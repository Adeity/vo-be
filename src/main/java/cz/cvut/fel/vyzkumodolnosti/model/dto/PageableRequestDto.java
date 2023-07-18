package cz.cvut.fel.vyzkumodolnosti.model.dto;

import cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi.MethodDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableRequestDto {

    private String researchNumberQueryString;
    private MethodDto method;
    private int pageLimit;
    private int pageNum;
}
