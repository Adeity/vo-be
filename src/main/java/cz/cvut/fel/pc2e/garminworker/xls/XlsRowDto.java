package cz.cvut.fel.pc2e.garminworker.xls;

import java.util.Collection;

public interface XlsRowDto {
	Collection<XlsDto> getParameters();
	String getResearchNumberValue();
}