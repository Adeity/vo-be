package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import cz.cvut.fel.vyzkumodolnosti.model.dto.PageableRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateGlobalChronoValuesDto {

    List<SingleGlobalValueDto> data;
    PageableRequestDto pageInfo;
}
