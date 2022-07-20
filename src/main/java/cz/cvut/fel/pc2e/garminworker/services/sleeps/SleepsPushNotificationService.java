package cz.cvut.fel.pc2e.garminworker.services.sleeps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepsPushNotificationDto;
import org.springframework.stereotype.Service;

@Service
public class SleepsPushNotificationService {
    public SleepsPushNotificationDto mapStringToDto(String pBody) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return om.readValue(pBody, SleepsPushNotificationDto.class);
    }
}
