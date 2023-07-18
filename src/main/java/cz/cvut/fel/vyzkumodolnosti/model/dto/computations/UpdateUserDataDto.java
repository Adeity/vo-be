package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import cz.cvut.fel.vyzkumodolnosti.model.dto.PageableRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDataDto {

    UserComputationDataDto data;
    PageableRequestDto pageInfo;
}
