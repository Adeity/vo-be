package cz.cvut.fel.vyzkumodolnosti.xls;

import java.util.Collection;

public interface XlsRowDto {
	Collection<XlsDto> getParameters();
	String getResearchNumberValue();
}