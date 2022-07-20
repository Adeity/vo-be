package cz.cvut.fel.pc2e.garminworker.repository;

import cz.cvut.fel.pc2e.garminworker.model.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceDao extends JpaRepository<DeviceEntity, Integer> {
    Optional<DeviceEntity> findByDeviceId(String deviceId);
    Optional<DeviceEntity> findByRequestToken(String requestToken);
    Optional<DeviceEntity> findByOauthToken(String oauthToken);
    @Query(value = "SELECT d FROM DeviceEntity d WHERE :oauthToken = d.oauthToken")
    List<DeviceEntity> findDeviceEntitiesByOauthToken(String oauthToken);
    @Query(value = "SELECT d FROM DeviceEntity d WHERE :userId = d.userId")
    List<DeviceEntity> findDeviceEntitiesByUserId(String userId);
    @Modifying
    @Query(value = "update DeviceEntity d set d.userId = :userId where d.id = :id")
    void updateUserId(int id, String userId);
}
