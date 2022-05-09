package cz.cvut.fel.pc2e.garminworker.dao;

import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.entities.sleeps.SleepSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SleepSummaryDao extends JpaRepository<SleepSummary, Integer> {
    Optional<SleepSummary> findBySummaryId(String deviceId);
}
