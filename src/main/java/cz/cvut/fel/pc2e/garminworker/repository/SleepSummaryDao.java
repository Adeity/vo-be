package cz.cvut.fel.pc2e.garminworker.repository;

import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SleepSummaryDao extends JpaRepository<SleepSummary, Integer> {
    Optional<SleepSummary> findBySummaryId(String deviceId);
    @Query(value = "SELECT s " +
            "FROM SleepSummary s WHERE s.startTimeInSeconds > :timeBoundary " +
            "ORDER BY s.userAccessToken, s.startTimeInSeconds")
    List<SleepSummary> findAllSorted(Long timeBoundary);
}
