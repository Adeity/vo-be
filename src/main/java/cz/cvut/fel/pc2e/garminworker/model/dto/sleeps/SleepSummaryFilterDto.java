package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;
import java.util.stream.Collectors;

public class SleepSummaryFilterDto {
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private Set<String> researchIds;

	public SleepSummaryFilterDto(Long from, Long to, Set<String> researchNumbers) {
		LocalDate dateFrom = Instant.ofEpochMilli(from).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dateTo = Instant.ofEpochMilli(to).atZone(ZoneId.systemDefault()).toLocalDate();
		this.setDateFrom(dateFrom);
		this.setDateTo(dateTo);
		this.setResearchIds(researchNumbers.stream().filter(e -> !e.isEmpty()).collect(Collectors.toSet()));
	}

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
