package cz.cvut.fel.pc2e.garminworker.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserIdReplyDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("errorMessage")
    private String errorMessage;
}
