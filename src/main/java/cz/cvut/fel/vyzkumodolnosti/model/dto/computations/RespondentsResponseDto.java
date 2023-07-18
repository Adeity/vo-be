package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespondentsResponseDto {

    long respondentsNum;
    int activePage;
    List<SleepRespondentDto> respondentData;
}
