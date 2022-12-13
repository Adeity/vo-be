package cz.cvut.fel.vyzkumodolnosti.repository.sleep;

import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SleepSummaryJpaRepository extends JpaRepository<SleepSummary, Integer> {
	Optional<SleepSummary> findBySummaryId(String deviceId);
	@Query(value = "SELECT s " +
			"FROM SleepSummary s WHERE s.startTimeInSeconds > :timeBoundary " +
			"ORDER BY s.userAccessToken, s.startTimeInSeconds")
	List<SleepSummary> findAllSorted(Long timeBoundary);


	@Query(value = "SELECT s " +
			"FROM SleepSummary s " +
			"WHERE " +
			"s.startTimeInSeconds >= :fromSeconds and " +
			"s.startTimeInSeconds <= :toSeconds and "+
			"s.device.researchNumber in :researchIds " +
			"ORDER BY s.userAccessToken, s.startTimeInSeconds")
	List<SleepSummary> findFilteredByDateAndResearchIds(long fromSeconds, long toSeconds, Set<String> researchIds);
}
