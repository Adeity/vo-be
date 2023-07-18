package cz.cvut.fel.vyzkumodolnosti.services.computations.respondent;

import cz.cvut.fel.vyzkumodolnosti.model.dto.PageableRequestDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.RespondentsResponseDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.SleepRespondentDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.UserComputationDataDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.Method;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceComputationForm;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.SleepComputationFormRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.UserComputationDataRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.device.DeviceComputationFormRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.SleepComputationFormMapper;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.UserComputationDataMapper;
import cz.cvut.fel.vyzkumodolnosti.services.device.mappers.DeviceComputationFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SleepRespondentService {

    @Autowired
    private SleepComputationFormRepository formsRepository;

    @Autowired
    private DeviceComputationFormRepository deviceRepository;

    @Autowired
    private UserComputationDataRepository userDataRepository;

    @Autowired
    private UserComputationDataMapper computationDataMapper;
    @Autowired
    private ResearchParticipantRepository researchParticipantRepository;

    @Autowired
    private SleepComputationFormMapper sleepComputationFormMapper;

    @Autowired
    private DeviceComputationFormMapper deviceComputationFormMapper;

    public List<SleepRespondentDto> getRespondentData() {

        List<SleepComputationForm> formsData = formsRepository.findAll();
        List<DeviceComputationForm> deviceData = deviceRepository.findAll();
        List<UserComputationData> userData = userDataRepository.findAll();

        HashMap<String, UserComputationDataDto> userDataMap = (HashMap<String, UserComputationDataDto>) userData.stream()
                .map(e -> computationDataMapper.entityToDto(e))
                .collect(Collectors.toMap(UserComputationDataDto::getResearchNumber, data -> data));

        HashMap<String, SleepRespondentDto> respondentMap = new HashMap<>();

        SleepComputationFormMapper formMapper = new SleepComputationFormMapper();
        for (SleepComputationForm computation : formsData) {
            if (!respondentMap.containsKey(computation.getPersonId())) {
                SleepRespondentDto respDto = new SleepRespondentDto();
                respDto.setLatencyFaThreshold(userDataMap.get(computation.getPersonId()).getLatencyFaThreshold());
                respDto.setSocJetlagThreshold(userDataMap.get(computation.getPersonId()).getSocJetlagThreshold());
                respDto.setFormComputations(new ArrayList<>());
                respDto.setId(computation.getPersonId());
                respondentMap.put(computation.getPersonId(), respDto);
            }
            respondentMap.get(computation.getPersonId()).getFormComputations().add(
                formMapper.entityToDto(computation)
            );
        }

        DeviceComputationFormMapper deviceMapper = new DeviceComputationFormMapper();
        for (DeviceComputationForm computation : deviceData) {
            if(!respondentMap.containsKey(computation.getPersonId())) {
                SleepRespondentDto respDto = new SleepRespondentDto();
                respDto.setLatencyFaThreshold(userDataMap.get(computation.getPersonId()).getLatencyFaThreshold());
                respDto.setSocJetlagThreshold(userDataMap.get(computation.getPersonId()).getSocJetlagThreshold());
                respDto.setDeviceComputations(new ArrayList<>());
                respDto.setId(computation.getPersonId());
                respondentMap.put(computation.getPersonId(), respDto);
            } else if(respondentMap.get(computation.getPersonId()).getDeviceComputations() == null) {
                respondentMap.get(computation.getPersonId()).setDeviceComputations(new ArrayList<>());
            }
            respondentMap.get(computation.getPersonId()).getDeviceComputations().add(
                deviceMapper.entityToDto(computation)
            );
        }

        return new ArrayList<>(respondentMap.values());
    }

    public RespondentsResponseDto getRespondentDataPageable(PageableRequestDto request) {

        List<ResearchParticipant> rps;
        String queryRegex = "%" + request.getResearchNumberQueryString() + "%";
        long totalRespondentsNumber;

        if (request.getMethod() != null) {

            Method method = new Method();
            method.setName(request.getMethod().getTitle());
            method.setId(request.getMethod().getId());

            totalRespondentsNumber = userDataRepository.countAllWithMethod(queryRegex, method.getId());

            rps = researchParticipantRepository.findByMethodsIsContainingAndResearchNumberLike(
                    method,
                    queryRegex,
                    PageRequest.of(
                            request.getPageNum(),
                            request.getPageLimit()
                    )
             );
        } else {

            totalRespondentsNumber = userDataRepository.countAll(queryRegex);

            rps = researchParticipantRepository.findByResearchNumberLike(
                    queryRegex,
                    PageRequest.of(
                            request.getPageNum(),
                            request.getPageLimit()
                    )
            );
        }

        List<String> rpNumbers = rps.stream().map(ResearchParticipant::getResearchNumber).collect(Collectors.toList());

        HashMap<String, SleepRespondentDto> respondentMap = new HashMap<>();

        List<SleepComputationForm> formsData = formsRepository.findAllByResearchParticipantResearchNumberIn(rpNumbers);
        List<DeviceComputationForm> deviceData = deviceRepository.findAllByResearchParticipantResearchNumberIn(rpNumbers);
        List<UserComputationData> userData = userDataRepository.findAllByResearchParticipantResearchNumberIn(rpNumbers);

        HashMap<String, UserComputationDataDto> userDataMap = (HashMap<String, UserComputationDataDto>) userData.stream()
                .map(e -> computationDataMapper.entityToDto(e))
                .collect(Collectors.toMap(UserComputationDataDto::getResearchNumber, data -> data));
        rpNumbers.stream()
        .filter(rpn ->userDataMap.get(rpn) != null)
        .forEach(
            rpn -> {
                SleepRespondentDto respDto = new SleepRespondentDto();
                respDto.setLatencyFaThreshold(userDataMap.get(rpn).getLatencyFaThreshold());
                respDto.setSocJetlagThreshold(userDataMap.get(rpn).getSocJetlagThreshold());
                respDto.setFormComputations(new ArrayList<>());
                respDto.setDeviceComputations(new ArrayList<>());
                respondentMap.put(rpn, respDto);
                respDto.setId(rpn);
            }
        );

        for (SleepComputationForm computation : formsData) {
            respondentMap.get(computation.getPersonId()).getFormComputations().add(
                    sleepComputationFormMapper.entityToDto(computation)
            );
        }

        for(DeviceComputationForm computation : deviceData) {
            respondentMap.get(computation.getPersonId()).getDeviceComputations().add(
                    deviceComputationFormMapper.entityToDto(computation)
            );
        }


        RespondentsResponseDto response = new RespondentsResponseDto();
        response.setRespondentsNum(totalRespondentsNumber);
        response.setRespondentData(new ArrayList<>(respondentMap.values()));
        response.setActivePage(request.getPageNum());

        return response;
    }

}
