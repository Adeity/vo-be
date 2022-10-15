package cz.cvut.fel.vyzkumodolnosti.xls;

import java.util.List;

public interface EntityToXlsRowDtoConverter<T> {
	List<XlsRowDto> convertEntitiesToXlsDto(List<T> entity);
}