package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class SleepSummaryFilterDto {
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private Set<String> researchIds;

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public Set<String> getResearchIds() {
		return researchIds;
	}

	public void setResearchIds(Set<String> researchIds) {
		this.researchIds = researchIds;
	}
}
