package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.entities.PeriodOffsetEntity;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.ActivityMessageProducer;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.RawMessageProducer;
import cz.cvut.fel.pc2e.garminworker.repositories.DeviceRepository;
import cz.cvut.fel.pc2e.garminworker.repositories.PeriodOffsetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
class HealthEpochControllerTest {

    private final static String OAUTH_TOKEN_1 = "oauthToken1";
    private final static int DEVICE_1_ID = 1;
    private final static String OAUTH_TOKEN_2 = "oauthToken2";
    private final static long EPOCH_START_TIME = 1637453700L;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    PeriodOffsetRepository periodOffsetRepository;

    @Mock
    RawMessageProducer rawMessageProducer;

    @Mock
    ActivityMessageProducer activityMessageProducer;

    @Mock
    DeviceEntity deviceEntityMock1;

    @Mock
    DeviceEntity deviceEntityMock2;

    @Mock
    PeriodOffsetEntity periodOffsetEntityMock;

    @InjectMocks
    HealthEpochController healthEpochController;

    @Test
    void testDailies_valid() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_2)).thenReturn(Optional.of(deviceEntityMock2));

        String jsonData = prepareJsonData();

        ResponseEntity<Serializable> result = healthEpochController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_2);
        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        // in total 30 minutes
        verify(activityMessageProducer, times(30)).sendActivityMessage(any());
        verify(periodOffsetRepository, times(2)).delete(any());
    }

    @Test
    void testDailies_valid_2Activities_in_block_should_load_block_offset() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(deviceEntityMock1.getId()).thenReturn(DEVICE_1_ID);
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(periodOffsetRepository.findByDeviceIdAndEpochStartTime(DEVICE_1_ID, new Timestamp(EPOCH_START_TIME * 1000))).thenReturn(Optional.of(periodOffsetEntityMock));
        when(periodOffsetEntityMock.getOffsetInPeriod()).thenReturn(300);

        String jsonData = prepareJsonDataTwoActivitiesOneBlock();

        ResponseEntity<Serializable> result = healthEpochController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        // 5 minutes was in previous block
        verify(activityMessageProducer, times(10)).sendActivityMessage(any());
        verify(periodOffsetRepository, times(1)).delete(any());
        verify(periodOffsetRepository, times(0)).save(any());
    }

    @Test
    void testDailies_valid_2Activities_in_block_should_save_block_offset() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(deviceEntityMock1.getId()).thenReturn(DEVICE_1_ID);
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(periodOffsetRepository.findByDeviceIdAndEpochStartTime(DEVICE_1_ID, new Timestamp(EPOCH_START_TIME * 1000))).thenReturn(Optional.empty());

        String jsonData = prepareJsonDataTwoActivitiesOneBlock();

        ResponseEntity<Serializable> result = healthEpochController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        verify(activityMessageProducer, times(11)).sendActivityMessage(any());
        verify(periodOffsetRepository, times(0)).delete(any());
        verify(periodOffsetRepository, times(1)).save(any());
    }

    @Test
    void testDeregistration_deviceNotFound() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.empty());
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_2)).thenReturn(Optional.empty());

        String jsonData = prepareJsonData();

        ResponseEntity<Serializable> result = healthEpochController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());


        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_2);
        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        verify(activityMessageProducer, times(0)).sendActivityMessage(any());
        verify(periodOffsetRepository, times(0)).delete(any());
    }

    @Test
    void testDeregistration_invalidRequest_shouldNotFail() {
        String jsonData = "{\"invalid\": 123}";

        ResponseEntity<Serializable> result = healthEpochController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        verify(activityMessageProducer, times(0)).sendActivityMessage(any());
    }

    private String prepareJsonData() {
        return "{\n" +
                "    \"epochs\": [\n" +
                "        {\n" +
                "            \"activeKilocalories\": 4,\n" +
                "            \"activeTimeInSeconds\": 900,\n" +
                "            \"activityType\": \"WALKING\",\n" +
                "            \"distanceInMeters\": 137.64,\n" +
                "            \"durationInSeconds\": 900,\n" +
                "            \"intensity\": \"ACTIVE\",\n" +
                "            \"maxMotionIntensity\": 4,\n" +
                "            \"meanMotionIntensity\": 3.5,\n" +
                "            \"met\": 1.4490178,\n" +
                "            \"startTimeInSeconds\": 1637453700,\n" +
                "            \"startTimeOffsetInSeconds\": 3600,\n" +
                "            \"steps\": 169,\n" +
                "            \"summaryId\": \"x420295e-61998f84-6\",\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_1 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"activeKilocalories\": 0,\n" +
                "            \"activeTimeInSeconds\": 900,\n" +
                "            \"activityType\": \"SEDENTARY\",\n" +
                "            \"distanceInMeters\": 0,\n" +
                "            \"durationInSeconds\": 900,\n" +
                "            \"intensity\": \"SEDENTARY\",\n" +
                "            \"maxMotionIntensity\": 4,\n" +
                "            \"meanMotionIntensity\": 0,\n" +
                "            \"met\": 1,\n" +
                "            \"startTimeInSeconds\": 1637453700,\n" +
                "            \"startTimeOffsetInSeconds\": 3600,\n" +
                "            \"steps\": 0,\n" +
                "            \"summaryId\": \"x420295e-61998f84-8\",\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_2 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private String prepareJsonDataTwoActivitiesOneBlock() {
        return "{\n" +
                "    \"epochs\": [\n" +
                "        {\n" +
                "            \"activeKilocalories\": 0,\n" +
                "            \"activeTimeInSeconds\": 625,\n" +
                "            \"activityType\": \"SEDENTARY\",\n" +
                "            \"distanceInMeters\": 0,\n" +
                "            \"durationInSeconds\": 900,\n" +
                "            \"intensity\": \"SEDENTARY\",\n" +
                "            \"maxMotionIntensity\": 4,\n" +
                "            \"meanMotionIntensity\": 0,\n" +
                "            \"met\": 1,\n" +
                "            \"startTimeInSeconds\": " + EPOCH_START_TIME + ",\n " +
                "            \"startTimeOffsetInSeconds\": 3600,\n" +
                "            \"steps\": 0,\n" +
                "            \"summaryId\": \"x420295e-61998f84-8\",\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_1 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}