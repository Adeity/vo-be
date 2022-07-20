package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SleepLevelTimeRangeDto {

    @JsonProperty("startTimeInSeconds")
    private Long startTimeInSeconds;

    @JsonProperty("endTimeInSeconds")
    private Long endTimeInSeconds;
}


