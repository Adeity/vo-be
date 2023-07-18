package cz.cvut.fel.vyzkumodolnosti.jobs.device;

import cz.cvut.fel.vyzkumodolnosti.handler.IncompleteFormsException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.device.DeviceComputationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class DeviceEvaluationScheduler {

    @Autowired
    private DeviceComputationService deviceComputationService;
    @Autowired
    private ResearchParticipantRepository researchParticipantRepository;

    @Scheduled(cron = "0 0 23 * * *")
    public void evaluateFromDevice() {
        log.info("Computing from device!");
        int day = LocalDate.now().getDayOfWeek().getValue();
        List<ResearchParticipant> rps = researchParticipantRepository.findByDeviceEntityRegistrationDayAndDeviceEntityDeregistrationTimeIsNull(day);

        for(var researchParticipant : rps) {
            try {
                deviceComputationService.computeStandard(researchParticipant);
            } catch(IncompleteFormsException e) {
                log.error("Exception occured! " + e.getMessage());
            }
        }
        log.info("Computation from device complete.");
    }

}
