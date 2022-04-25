package cz.cvut.fel.pc2e.garminworker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DailiesPushNotificationDto {

    @JsonProperty("dailies")
    private List<DailySummaryDto> dailies;
}