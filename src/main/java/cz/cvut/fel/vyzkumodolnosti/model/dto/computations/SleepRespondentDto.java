package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceComputationFormDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SleepRespondentDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String id;
    private List<SleepComputationFormDto> formComputations;
    private List<DeviceComputationFormDto> deviceComputations;

    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String socJetlagThreshold;
    private Integer latencyFaThreshold;
}
