package cz.cvut.fel.vyzkumodolnosti.model.dto;

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
