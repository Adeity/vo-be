package cz.cvut.fel.pc2e.garminworker.cloud.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SleepLevelTimeRange {

    @JsonProperty("startTimeInSeconds")
    private Long startTimeInSeconds;

    @JsonProperty("endTimeInSeconds")
    private Long endTimeInSeconds;
}


