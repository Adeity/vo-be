package cz.cvut.fel.pc2e.garminworker.repository;

import cz.cvut.fel.pc2e.garminworker.model.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DeviceDao extends JpaRepository<DeviceEntity, Integer> {
    Optional<DeviceEntity> findByResearchNumber(String researchNumber);
    Optional<DeviceEntity> findByRequestToken(String requestToken);
    Optional<DeviceEntity> findByUserAccessToken(String userAccessToken);
    @Query(value = "SELECT d FROM DeviceEntity d WHERE :userAccessToken = d.userAccessToken")
    List<DeviceEntity> findDeviceEntitiesByUserAccessToken(String userAccessToken);
    @Query(value = "SELECT d FROM DeviceEntity d WHERE :userId = d.userId")
    List<DeviceEntity> findDeviceEntitiesByUserId(String userId);
    @Modifying
    @Query(value = "update DeviceEntity d set d.userId = :userId where d.id = :id")
    void updateUserId(int id, String userId);
	@Query(value = "select d.id from DeviceEntity d where :userAccessToken = d.userAccessToken")
	Optional<Integer> findDeviceIdByUserAccessToken(String userAccessToken);
	@Query(value = "select d.researchNumber from DeviceEntity d")
	Set<String> findAllResearchNumbers();

	@Query(value = "select d.researchNumber from DeviceEntity d where d.allowed = true and d.oauthTokenSecret is not null")
	List<String> findAllActiveResearchNumbers();
}
