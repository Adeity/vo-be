package cz.cvut.fel.vyzkumodolnosti.xls.converters;

import cz.cvut.fel.vyzkumodolnosti.xls.dtos.row.XlsRowDto;

import java.util.List;

public interface EntityToXlsRowDtoConverter<T> {
	List<XlsRowDto> convertEntitiesToXlsDto(List<T> entity);
}