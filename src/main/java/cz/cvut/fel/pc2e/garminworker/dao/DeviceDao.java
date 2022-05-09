package cz.cvut.fel.pc2e.garminworker.dao;

import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceDao extends JpaRepository<DeviceEntity, Integer> {
    Optional<DeviceEntity> findByDeviceId(String deviceId);

    Optional<DeviceEntity> findByRequestToken(String requestToken);

    Optional<DeviceEntity> findByOauthToken(String oauthToken);
}
