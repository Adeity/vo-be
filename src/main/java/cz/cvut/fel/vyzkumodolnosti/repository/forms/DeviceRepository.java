package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.DeviceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
    Optional<DeviceEntity> findByResearchNumber(String researchNumber);
    Optional<DeviceEntity> findByRequestToken(String requestToken);
    @Modifying
    @Query(value = "update DeviceEntity d set d.userId = :userId where d.id = :id")
    void updateUserId(int id, String userId);
	@Query(value = "select d.id from DeviceEntity d where :userAccessToken = d.userAccessToken")
	Optional<Integer> findDeviceIdByUserAccessToken(String userAccessToken);
	@Query(value = "select d.researchNumber from DeviceEntity d")
	Set<String> findAllResearchNumbers();
	@Query(value = "select d.researchNumber from DeviceEntity d where d.allowed = true and d.oauthTokenSecret is not null")
	List<String> findAllActiveResearchNumbers();
	Optional<DeviceEntity> findByUserAccessToken(String userAccessToken);
	boolean existsByUserAccessToken(String userAccessToken);
	boolean existsByUserAccessTokenAndId(String userAccessToken, int id);
	<T> Slice<T> findAllBy(Pageable e, Class<T> type);

	List<DeviceEntityInfo> findAllBy(Sort sort);

	@Transactional
	@Modifying
	@Query("update DeviceEntity d set d.researchNumber = ?1 where d.id = ?2")
	int updateResearchNumberById(String researchNumber, int id);

	@Query("select d from DeviceEntity d where d.researchNumber not like ?1")
	List<DeviceEntityInfo> findByResearchNumberNotLike(String researchNumber);

	@Query("select d from DeviceEntity d " +
			"where d.allowed = true or d.allowed = false and d.deregistrationTime is not null")
	List<DeviceEntityInfo> findByAllowedTrueOrAllowedFalseAndDeregistrationTimeNotNull(Sort sort);



}
