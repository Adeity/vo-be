package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SleepRespondentDto {

    private String id;
    private List<SleepComputationFormDto> computations;
}
