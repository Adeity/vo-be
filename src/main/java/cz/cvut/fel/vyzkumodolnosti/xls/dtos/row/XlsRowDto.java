package cz.cvut.fel.vyzkumodolnosti.xls.dtos.row;

import cz.cvut.fel.vyzkumodolnosti.xls.dtos.XlsDto;

import java.util.Collection;

public interface XlsRowDto {
	Collection<XlsDto> getParameters();
	String getResearchNumberValue();
}