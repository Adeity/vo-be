package cz.cvut.fel.pc2e.garminworker.services.sleeps;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.cvut.fel.pc2e.garminworker.JsonDataHolder;
import cz.cvut.fel.pc2e.garminworker.repository.DeviceDao;
import cz.cvut.fel.pc2e.garminworker.repository.SleepSummaryDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
class SleepsSummaryPushProcesserServiceTest {
	@Mock
	DeviceDao deviceDao;
	@Mock
	SleepSummaryDao sleepSummaryDao;
	@InjectMocks
	SleepsSummaryPushProcesserService service;

	private final static String ACCESS_TOKEN_1 = "accessToken1";
	private final static String ACCESS_TOKEN_2 = "accessToken2";

	StringToSleepPushNotificationMapper stringToSleepPushNotificationMapper = new StringToSleepPushNotificationMapper();

	@Test
	void testProcess() {
		when(deviceDao.findDeviceIdByUserAccessToken(ACCESS_TOKEN_1)).thenReturn(Optional.of(1));
		when(deviceDao.findDeviceIdByUserAccessToken(ACCESS_TOKEN_2)).thenReturn(Optional.of(1));

		try {
			service.processSleepsPushNotification(
					stringToSleepPushNotificationMapper.mapStringToDto(JsonDataHolder.prepareJsonData(ACCESS_TOKEN_1, ACCESS_TOKEN_2)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		verify(sleepSummaryDao, times(2)).save(Mockito.any());
	}

	@Test
	void test_process_no_device_found() {
		when(deviceDao.findDeviceIdByUserAccessToken(ACCESS_TOKEN_1)).thenReturn(Optional.empty());
		when(deviceDao.findDeviceIdByUserAccessToken(ACCESS_TOKEN_2)).thenReturn(Optional.empty());

		try {
			service.processSleepsPushNotification(
					stringToSleepPushNotificationMapper.mapStringToDto(JsonDataHolder.prepareJsonData(ACCESS_TOKEN_1, ACCESS_TOKEN_2)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		verify(sleepSummaryDao, times(0)).save(Mockito.any());
	}

	@Test
	void test_process_invalid_data_doesnt_fail() {
		try {
			service.processSleepsPushNotification(stringToSleepPushNotificationMapper.mapStringToDto(JsonDataHolder.prepareInvalidData()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		verify(sleepSummaryDao, times(0)).save(Mockito.any());
	}

}