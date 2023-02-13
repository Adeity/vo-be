package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComputationDataRepository extends JpaRepository<UserComputationData, Integer> {

    UserComputationData findByUserId(String id);
}
