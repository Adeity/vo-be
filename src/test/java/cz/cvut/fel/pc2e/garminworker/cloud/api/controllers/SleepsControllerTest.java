package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import cz.cvut.fel.pc2e.garminworker.controllers.SleepsController;
import cz.cvut.fel.pc2e.garminworker.model.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.repository.DeviceDao;
import org.junit.jupiter.api.Disabled;
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
@Disabled
class SleepsControllerTest {

    private final static String OAUTH_TOKEN_1 = "oauthToken1";
    private final static String OAUTH_TOKEN_2 = "oauthToken2";

    @Mock
    DeviceDao deviceRepository;

    @Mock
    DeviceEntity deviceEntityMock1;

    @Mock
    DeviceEntity deviceEntityMock2;

    @InjectMocks
    SleepsController sleepsController;

    @Test
    void testDailies_valid() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.of(deviceEntityMock1));
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_2)).thenReturn(Optional.of(deviceEntityMock2));

        String jsonData = prepareJsonData();

        ResponseEntity<Serializable> result = sleepsController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_2);
    }

    @Test
    void testDeregistration_deviceNotFound() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_1)).thenReturn(Optional.empty());
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN_2)).thenReturn(Optional.empty());

        String jsonData = prepareJsonData();

        ResponseEntity<Serializable> result = sleepsController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());


        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_1);
        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN_2);
    }

    @Test
    void testDeregistration_invalidRequest_shouldNotFail() {
        String jsonData = "{\"invalid\": 123}";

        ResponseEntity<Serializable> result = sleepsController.postData(jsonData);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private String prepareJsonData() {
        return "{\n" +
                "    \"sleeps\": [\n" +
                "        {\n" +
                "            \"awakeDurationInSeconds\": 0,\n" +
                "            \"calendarDate\": \"2021-11-13\",\n" +
                "            \"deepSleepDurationInSeconds\": 350,\n" +
                "            \"durationInSeconds\": 36000,\n" +
                "            \"lightSleepDurationInSeconds\": 22000,\n" +
                "            \"remSleepInSeconds\": 12000,\n" +
                "            \"sleepLevelsMap\": {\n" +
                "                \"deep\": [\n" +
                "                    {\n" +
                "                        \"endTimeInSeconds\": 1636846080,\n" +
                "                        \"startTimeInSeconds\": 1636845780\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"light\": [\n" +
                "                    {\n" +
                "                        \"endTimeInSeconds\": 1636845780,\n" +
                "                        \"startTimeInSeconds\": 1636845000\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"rem\": [\n" +
                "                    {\n" +
                "                        \"endTimeInSeconds\": 1636847160,\n" +
                "                        \"startTimeInSeconds\": 1636846200\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"startTimeInSeconds\": 1636845000,\n" +
                "            \"startTimeOffsetInSeconds\": 3600,\n" +
                "            \"summaryId\": \"x420295e-619045c8-86c4\",\n" +
                "            \"timeOffsetSleepSpo2\": {},\n" +
                "            \"unmeasurableSleepInSeconds\": 840,\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_1 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\",\n" +
                "            \"validation\": \"ENHANCED_TENTATIVE\"\n" +
                "        },\n" +
                "                {\n" +
                "            \"awakeDurationInSeconds\": 0,\n" +
                "            \"calendarDate\": \"2021-11-13\",\n" +
                "            \"deepSleepDurationInSeconds\": 350,\n" +
                "            \"durationInSeconds\": 36000,\n" +
                "            \"lightSleepDurationInSeconds\": 22000,\n" +
                "            \"remSleepInSeconds\": 12000,\n" +
                "            \"sleepLevelsMap\": {\n" +
                "                \"deep\": [\n" +
                "                    {\n" +
                "                        \"endTimeInSeconds\": 1636846080,\n" +
                "                        \"startTimeInSeconds\": 1636845780\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"light\": [\n" +
                "                    {\n" +
                "                        \"endTimeInSeconds\": 1636845780,\n" +
                "                        \"startTimeInSeconds\": 1636845000\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"rem\": [\n" +
                "                    {\n" +
                "                        \"endTimeInSeconds\": 1636847160,\n" +
                "                        \"startTimeInSeconds\": 1636846200\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"startTimeInSeconds\": 1636845000,\n" +
                "            \"startTimeOffsetInSeconds\": 3600,\n" +
                "            \"summaryId\": \"x420295e-619045c8-86c4\",\n" +
                "            \"timeOffsetSleepSpo2\": {},\n" +
                "            \"unmeasurableSleepInSeconds\": 840,\n" +
                "            \"userAccessToken\": \"" + OAUTH_TOKEN_2 + "\",\n" +
                "            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\",\n" +
                "            \"validation\": \"ENHANCED_TENTATIVE\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}