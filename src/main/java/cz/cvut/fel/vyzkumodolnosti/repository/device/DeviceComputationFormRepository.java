package cz.cvut.fel.vyzkumodolnosti.repository.device;


import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceComputationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceComputationFormRepository extends JpaRepository<DeviceComputationForm, Long> {

    List<DeviceComputationForm> findAllByResearchParticipantResearchNumberIn(List<String> researchNumberList);

    List<DeviceComputationForm> findAllByRecalculations(Integer version);

    List<DeviceComputationForm> findAllByResearchParticipantResearchNumberAndRecalculations(String researchNumber, Integer version);

    Integer countAllByVersion(Long version);
}
