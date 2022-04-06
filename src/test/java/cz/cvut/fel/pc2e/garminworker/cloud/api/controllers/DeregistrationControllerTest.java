package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.DeregistrationDto;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.DeregistrationPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.repositories.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
class DeregistrationControllerTest {

    private final static String OAUTH_TOKEN = "oauthToken";

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    DeviceEntity deviceEntityMock;

    @InjectMocks
    DeregistrationController deregistrationController;

    @Test
    void testDeregistration_valid() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN)).thenReturn(Optional.of(deviceEntityMock));

        DeregistrationPushNotificationDto deregistrationPushNotificationDto = prepareDto();

        ResponseEntity<Serializable> result = deregistrationController.deregisterDevice(deregistrationPushNotificationDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN);
        verify(deviceRepository, times(1)).save(any());
        verify(deviceEntityMock, times(1)).setAllowed(false);
    }

    @Test
    void testDeregistration_deviceNotFound() {
        when(deviceRepository.findByOauthToken(OAUTH_TOKEN)).thenReturn(Optional.empty());

        DeregistrationPushNotificationDto deregistrationPushNotificationDto = prepareDto();

        ResponseEntity<Serializable> result = deregistrationController.deregisterDevice(deregistrationPushNotificationDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(deviceRepository, times(1)).findByOauthToken(OAUTH_TOKEN);
        verify(deviceRepository, times(0)).save(any());
        verify(deviceEntityMock, times(0)).setAllowed(false);
    }

    private DeregistrationPushNotificationDto prepareDto() {
        DeregistrationPushNotificationDto deregistrationPushNotificationDto = new DeregistrationPushNotificationDto();
        DeregistrationDto deregistrationDto = new DeregistrationDto();
        deregistrationDto.setUserAccessToken(OAUTH_TOKEN);
        List<DeregistrationDto> deregistrationDtoList = new ArrayList<>();
        deregistrationDtoList.add(deregistrationDto);
        deregistrationPushNotificationDto.setDeregistrations(deregistrationDtoList);

        return deregistrationPushNotificationDto;
    }
}