package cz.cvut.fel.pc2e.garminworker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeregistrationDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userAccessToken")
    private String userAccessToken;
}


