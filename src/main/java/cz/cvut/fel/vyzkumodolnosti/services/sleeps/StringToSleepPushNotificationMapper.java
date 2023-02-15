package cz.cvut.fel.vyzkumodolnosti.services.sleeps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepsPushNotificationDto;

public class StringToSleepPushNotificationMapper {
	public SleepsPushNotificationDto mapStringToDto(String pBody) throws JsonProcessingException {

		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return om.readValue(pBody, SleepsPushNotificationDto.class);
	}
}
