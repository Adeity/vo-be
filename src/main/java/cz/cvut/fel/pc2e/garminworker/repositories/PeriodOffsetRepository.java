package cz.cvut.fel.pc2e.garminworker.repositories;

import cz.cvut.fel.pc2e.garminworker.entities.PeriodOffsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface PeriodOffsetRepository extends JpaRepository<PeriodOffsetEntity, Integer> {
    Optional<PeriodOffsetEntity> findByDeviceIdAndEpochStartTime(Integer deviceId, Timestamp epochStartTime);
}
