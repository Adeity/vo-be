package cz.cvut.fel.pc2e.garminworker.repositories;

import cz.cvut.fel.pc2e.garminworker.entities.PeriodOffsetEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
class PeriodOffsetRepositoryTest {

    private final static Integer DEVICE_ID = 1;
    private final static Timestamp EPOCH_START_TIME = new Timestamp(System.currentTimeMillis());
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private PeriodOffsetRepository periodOffsetRepository;

    private void insertPeriodOffset() {
        PeriodOffsetEntity periodOffsetEntity = new PeriodOffsetEntity();
        periodOffsetEntity.setDeviceId(DEVICE_ID);
        periodOffsetEntity.setEpochStartTime(EPOCH_START_TIME);

        testEntityManager.persist(periodOffsetEntity);
        testEntityManager.flush();
    }

    @BeforeEach
    public void setUp() {
        insertPeriodOffset();
    }

    @AfterEach
    public void tearDown() {
        testEntityManager.clear();
    }

    @Test
    void findByDeviceIdAndEpochStartTime_shouldFind() {
        Optional<PeriodOffsetEntity> periodOffsetEntityOptional = periodOffsetRepository.findByDeviceIdAndEpochStartTime(DEVICE_ID, EPOCH_START_TIME);

        verifyPeriodOffsetExistsAndCorrect(periodOffsetEntityOptional);
    }

    @Test
    void findByDeviceIdAndEpochStartTime_shouldNotFind_wrongTime() {
        Optional<PeriodOffsetEntity> periodOffsetEntityOptional = periodOffsetRepository.findByDeviceIdAndEpochStartTime(DEVICE_ID, new Timestamp(System.currentTimeMillis()));

        verifyPeriodOffsetNotExists(periodOffsetEntityOptional);
    }

    @Test
    void findByDeviceIdAndEpochStartTime_shouldNotFind_wrongId() {
        Optional<PeriodOffsetEntity> periodOffsetEntityOptional = periodOffsetRepository.findByDeviceIdAndEpochStartTime(DEVICE_ID + 1, EPOCH_START_TIME);

        verifyPeriodOffsetNotExists(periodOffsetEntityOptional);
    }

    private void verifyPeriodOffsetExistsAndCorrect(Optional<PeriodOffsetEntity> periodOffsetEntityOptional) {
        assertTrue(periodOffsetEntityOptional.isPresent());

        PeriodOffsetEntity foundEntity = periodOffsetEntityOptional.get();

        assertEquals(DEVICE_ID, foundEntity.getDeviceId());
        assertEquals(EPOCH_START_TIME, foundEntity.getEpochStartTime());
    }

    private void verifyPeriodOffsetNotExists(Optional<PeriodOffsetEntity> periodOffsetEntityOptional) {
        Assertions.assertFalse(periodOffsetEntityOptional.isPresent());
    }
}