package cz.cvut.fel.vyzkumodolnosti.services.computations.respondent;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.SleepRespondentDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.SleepComputationFormRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.SleepComputationFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SleepRespondentService {

    @Autowired
    private SleepComputationFormRepository repository;

    public List<SleepRespondentDto> getRespondentData() {

        List<SleepComputationForm> respData = repository.findAll();

        HashMap<String, SleepRespondentDto> respondentMap = new HashMap<>();

        SleepComputationFormMapper mapper = new SleepComputationFormMapper();
        for (SleepComputationForm computation : respData) {
            if (!respondentMap.containsKey(computation.getPersonId())) {
                SleepRespondentDto respDto = new SleepRespondentDto();
                respDto.setComputations(new ArrayList<>());
                respDto.setId(computation.getPersonId());
                respondentMap.put(computation.getPersonId(), respDto);
            }
            respondentMap.get(computation.getPersonId()).getComputations().add(
                mapper.entityToDto(computation)
            );
        }

        return new ArrayList<>(respondentMap.values());
    }
}
