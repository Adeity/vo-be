package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import cz.cvut.fel.pc2e.garminworker.controllers.DailiesController;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.HeartRateMessageProducer;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.RawMessageProducer;
import cz.cvut.fel.pc2e.garminworker.dao.DeviceDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.io.Serializable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
class DailiesControllerTest {

    private final static String OAUTH_TOKEN_1 = "oauthToken1";
    private final static String OAUTH_TOKEN_2 = "oauthToken2";

    @Mock
    DeviceDao deviceRepository;

    @Mock
    RawMessageProducer rawMessageProducer;

    @Mock
    HeartRateMessageProducer heartRateMessageProducer;

    @Mock
    DeviceEntity deviceEntityMock1;

    @Mock
    DeviceEntity deviceEntityMock2;

    @InjectMocks
    DailiesController dailiesController;

    @Test
    void testDailies_valid() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_2)).thenReturn(Optional.of(deviceEntityMock2));

        String jsonData = prepareJsonData();

        ResponseEntity<Serializable> result = dailiesController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_2);
        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        verify(heartRateMessageProducer, times(10)).sendHeartRateMessage(any());
    }

    @Test
    void testDeregistration_deviceNotFound() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.empty());
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_2)).thenReturn(Optional.empty());

        String jsonData = prepareJsonData();

        ResponseEntity<Serializable> result = dailiesController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());


        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_2);
        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        verify(heartRateMessageProducer, times(0)).sendHeartRateMessage(any());
    }

    @Test
    void testDeregistration_invalidRequest_shouldNotFail() {
        String jsonData = "{\"invalid\": 123}";

        ResponseEntity<Serializable> result = dailiesController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(rawMessageProducer, times(1)).sendRawMessage(any());
        verify(rawMessageProducer, times(0)).sendRawDltMessage(any());
        verify(heartRateMessageProducer, times(0)).sendHeartRateMessage(any());
    }

    private String prepareJsonData() {
        return "{\n" +
                "    \"dailies\": [\n" +
                "        {\n" +
                "            \"activeKilocalories\": 64,\n" +
                "            \"activeTimeInSeconds\": 443,\n" +
                "            \"activityStressDurationInSeconds\": 74,\n" +
                "            \"activityType\": \"WALKING\",\n" +
                "            \"averageHeartRateInBeatsPerMinute\": 80,\n" +
                "            \"averageStressLevel\": 32,\n" +
                "            \"bmrKilocalories\": 63,\n" +
                "            \"distanceInMeters\": 324.1,\n" +
                "            \"durationInSeconds\": 86400,\n" +
                "            \"floorsClimbed\": 0,\n" +
                "            \"floorsClimbedGoal\": 12,\n" +
                "            \"highStressDurationInSeconds\": 51,\n" +
                "            \"intensityDurationGoalInSeconds\": 7200,\n" +
                "            \"lowStressDurationInSeconds\": 50,\n" +
                "            \"maxHeartRateInBeatsPerMinute\": 79,\n" +
                "            \"maxStressLevel\": 13,\n" +
                "            \"mediumStressDurationInSeconds\": 69,\n" +
                "            \"minHeartRateInBeatsPerMinute\": 51,\n" +
                "            \"moderateIntensityDurationInSeconds\": 4500,\n" +
                "            \"netKilocaloriesGoal\": 12392,\n" +
                "            \"restStressDurationInSeconds\": 192,\n" +
                "            \"restingHeartRateInBeatsPerMinute\": 58,\n" +
                "            \"startTimeInSeconds\": 1636335720,\n" +
                "            \"startTimeOffsetInSeconds\": -21600,\n" +
                "            \"steps\": 66,\n" +
                "            \"stepsGoal\": 9956,\n" +
                "            \"stressDurationInSeconds\": 726,\n" +
                "            \"summaryId\": \"sd420295e-61888068-15180-6\",\n" +
                "            \"timeOffsetHeartRateSamples\": {\n" +
                "                \"11825\": 52,\n" +
                "                \"17535\": 79,\n" +
                "                \"18811\": 51,\n" +
                "                \"19495\": 53,\n" +
                "                \"3567\": 51\n" +
                "            },\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_1 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\",\n" +
                "            \"vigorousIntensityDurationInSeconds\": 2220\n" +
                "        },\n" +
                "        {\n" +
                "            \"activeKilocalories\": 81,\n" +
                "            \"activeTimeInSeconds\": 984,\n" +
                "            \"activityStressDurationInSeconds\": 76,\n" +
                "            \"activityType\": \"WALKING\",\n" +
                "            \"averageHeartRateInBeatsPerMinute\": 60,\n" +
                "            \"averageStressLevel\": 35,\n" +
                "            \"bmrKilocalories\": 34,\n" +
                "            \"distanceInMeters\": 452.88,\n" +
                "            \"durationInSeconds\": 86400,\n" +
                "            \"floorsClimbed\": 3,\n" +
                "            \"floorsClimbedGoal\": 11,\n" +
                "            \"highStressDurationInSeconds\": 60,\n" +
                "            \"intensityDurationGoalInSeconds\": 5880,\n" +
                "            \"lowStressDurationInSeconds\": 35,\n" +
                "            \"maxHeartRateInBeatsPerMinute\": 79,\n" +
                "            \"maxStressLevel\": 24,\n" +
                "            \"mediumStressDurationInSeconds\": 62,\n" +
                "            \"minHeartRateInBeatsPerMinute\": 51,\n" +
                "            \"moderateIntensityDurationInSeconds\": 3900,\n" +
                "            \"netKilocaloriesGoal\": 9232,\n" +
                "            \"restStressDurationInSeconds\": 162,\n" +
                "            \"restingHeartRateInBeatsPerMinute\": 62,\n" +
                "            \"startTimeInSeconds\": 1636422120,\n" +
                "            \"startTimeOffsetInSeconds\": -21600,\n" +
                "            \"steps\": 1138,\n" +
                "            \"stepsGoal\": 6752,\n" +
                "            \"stressDurationInSeconds\": 530,\n" +
                "            \"summaryId\": \"sd420295e-6189d1e8-15180-6\",\n" +
                "            \"timeOffsetHeartRateSamples\": {\n" +
                "                \"11825\": 52,\n" +
                "                \"17535\": 79,\n" +
                "                \"18811\": 51,\n" +
                "                \"19495\": 53,\n" +
                "                \"3567\": 51\n" +
                "            },\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_2 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\",\n" +
                "            \"vigorousIntensityDurationInSeconds\": 1920\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}