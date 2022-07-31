package cz.cvut.fel.pc2e.garminworker.xls;

import java.util.List;

public interface EntityToXlsRowDtoConverter<T> {
	List<XlsRowDto> convertEntitiesToXlsDto(List<T> entity);
}