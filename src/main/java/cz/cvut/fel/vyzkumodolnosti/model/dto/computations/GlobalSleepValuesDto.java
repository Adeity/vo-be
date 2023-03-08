package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GlobalSleepValuesDto {

    @JsonProperty("values")
    private List<SingleGlobalValueDto> values;
}
