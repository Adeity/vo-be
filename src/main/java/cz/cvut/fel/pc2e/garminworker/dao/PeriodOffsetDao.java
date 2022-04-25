package cz.cvut.fel.pc2e.garminworker.dao;

import cz.cvut.fel.pc2e.garminworker.entities.PeriodOffsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface PeriodOffsetDao extends JpaRepository<PeriodOffsetEntity, Integer> {
    Optional<PeriodOffsetEntity> findByDeviceIdAndEpochStartTime(Integer deviceId, Timestamp epochStartTime);
}
