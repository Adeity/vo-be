package cz.cvut.fel.pc2e.garminworker.repositories;

import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
class DeviceRepositoryTest {

    private final static String DEVICE_ID = "deviceId";
    private final static String OAUTH_TOKEN = "oauthToken";
    private final static String REQUEST_TOKEN = "requestToken";
    private final static String INVALID = "invalid";
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DeviceRepository deviceRepository;

    private void insertDevice() {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceId(DEVICE_ID);
        deviceEntity.setOauthToken(OAUTH_TOKEN);
        deviceEntity.setRequestToken(REQUEST_TOKEN);

        testEntityManager.persist(deviceEntity);
        testEntityManager.flush();
    }

    @BeforeEach
    public void setUp() {
        insertDevice();
    }

    @AfterEach
    public void tearDown() {
        testEntityManager.clear();
    }

    @Test
    void findByDeviceId_shouldFind() {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findByDeviceId(DEVICE_ID);

        verifyDeviceExistsAndCorrect(deviceEntityOptional);
    }

    @Test
    void findByDeviceId_shouldNotFind() {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findByDeviceId(INVALID);

        verifyDeviceNotExists(deviceEntityOptional);
    }

    @Test
    void findByRequestToken_shouldFind() {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findByRequestToken(REQUEST_TOKEN);

        verifyDeviceExistsAndCorrect(deviceEntityOptional);
    }

    @Test
    void findByRequestToken_shouldNotFind() {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findByRequestToken(INVALID);

        verifyDeviceNotExists(deviceEntityOptional);
    }

    @Test
    void findByOauthToken_shouldFind() {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findByOauthToken(OAUTH_TOKEN);

        verifyDeviceExistsAndCorrect(deviceEntityOptional);
    }

    @Test
    void findByOauthToken_shouldNotFind() {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findByOauthToken(INVALID);

        verifyDeviceNotExists(deviceEntityOptional);
    }

    private void verifyDeviceNotExists(Optional<DeviceEntity> deviceEntityOptional) {
        Assertions.assertFalse(deviceEntityOptional.isPresent());
    }

    private void verifyDeviceExistsAndCorrect(Optional<DeviceEntity> deviceEntityOptional) {
        assertTrue(deviceEntityOptional.isPresent());

        DeviceEntity foundEntity = deviceEntityOptional.get();

        assertEquals(DEVICE_ID, foundEntity.getDeviceId());
        assertEquals(OAUTH_TOKEN, foundEntity.getOauthToken());
        assertEquals(REQUEST_TOKEN, foundEntity.getRequestToken());
    }
}