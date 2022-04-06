package cz.cvut.fel.pc2e.garminworker.cloud.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DeregistrationPushNotificationDto {

    @JsonProperty("deregistrations")
    private List<DeregistrationDto> deregistrations;
}
