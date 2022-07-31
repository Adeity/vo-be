package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SleepsPushNotificationDto {

    @JsonProperty("sleeps")
    private List<SleepSummaryDto> sleeps;
}