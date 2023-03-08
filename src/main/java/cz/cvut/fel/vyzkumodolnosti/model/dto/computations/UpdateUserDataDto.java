package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDataDto {

    @JsonProperty("userId")
    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String userId;

    @JsonProperty("socJetlagThreshold")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String socJetlagThreshold;

    @JsonProperty("latencyFaThreshold")
    private Integer latencyFaThreshold;
}
