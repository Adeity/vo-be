package cz.cvut.fel.pc2e.garminworker.services;

import cz.cvut.fel.pc2e.garminworker.dao.DeviceDao;
import cz.cvut.fel.pc2e.garminworker.dao.SleepSummaryDao;
import cz.cvut.fel.pc2e.garminworker.dto.SleepSummaryDto;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.entities.sleeps.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SleepsService {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private final DeviceDao deviceRepository;

    private final SleepSummaryDao sleepSummaryDao;

    @Autowired
    public SleepsService(DeviceDao deviceRepository, SleepSummaryDao sleepSummaryDao) {
        this.deviceRepository = deviceRepository;
        this.sleepSummaryDao = sleepSummaryDao;
    }

    public SleepSummary getSleepSummaryById(Integer id) {
        Optional<SleepSummary> s = sleepSummaryDao.findById(id);
        return s.orElse(null);
    }

    @Transactional
    public void processSleepSummaryDTO(SleepSummaryDto sleepSummaryDto) {
        log.debug("Processing sleepSummaryDTO: {} ", sleepSummaryDto);
        // first check if sleep summary with summaryId already exists
        Optional<SleepSummary> existingSummaryOpt = sleepSummaryDao.findBySummaryId(sleepSummaryDto.getSummaryId());
        // process SleepSummaryDto into SleepSummary business object
        SleepSummary sleepSummary = this.convertDtoToBusinessObject(sleepSummaryDto);

        if (sleepSummary.getUserAccessToken() == null) {
            log.warn("Skipping sleep summary, because userAccessToken is missing. sleep: {}", sleepSummary);
            return;
        }
        if (sleepSummary.getStartTimeInSeconds() == null || sleepSummary.getDurationInSeconds() == null) {
            log.warn("Skipping sleep summary, because startTime or duration is missing. sleep: {}", sleepSummary);
            return;
        }

        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByOauthToken(sleepSummary.getUserAccessToken());

        deviceEntityOpt.ifPresentOrElse(deviceEntity -> {
            log.debug("New sleep summary:     {}", sleepSummary);
            if (existingSummaryOpt.isPresent()) {
                SleepSummary existingSummary = existingSummaryOpt.get();
                log.debug("Existing sleep summary:     {}", existingSummary);
                log.debug("An updated summary came. Going to overwrite it.");

                sleepSummary.setId(existingSummaryOpt.get().getId());
            }

            this.sleepSummaryDao.save(sleepSummary);

        }, () -> log.error("Skipping sleep, because device with oAuthToken: {} was not found", sleepSummary.getUserAccessToken()));
    }

    private SleepSummary convertDtoToBusinessObject(SleepSummaryDto dto) {
        SleepSummary sleepSummary = new SleepSummary();
        sleepSummary.setUserId(dto.getUserId());
        sleepSummary.setUserAccessToken(dto.getUserAccessToken());
        sleepSummary.setSummaryId(dto.getSummaryId());
        sleepSummary.setCalendarDate(
                getCalendarDate(dto.getCalendarDate(), dto.getStartTimeInSeconds())
        );
        sleepSummary.setDurationInSeconds(dto.getDurationInSeconds());
        sleepSummary.setStartTimeInSeconds(dto.getStartTimeInSeconds());
        sleepSummary.setStartTimeOffsetInSeconds(dto.getStartTimeOffsetInSeconds());
        sleepSummary.setUnmeasurableSleepInSeconds(dto.getUnmeasurableSleepInSeconds());
        sleepSummary.setDeepSleepDurationInSeconds(dto.getDeepSleepDurationInSeconds());
        sleepSummary.setLightSleepDurationInSeconds(dto.getLightSleepDurationInSeconds());
        sleepSummary.setRemSleepInSeconds(dto.getRemSleepInSeconds());
        sleepSummary.setAwakeDurationInSeconds(dto.getAwakeDurationInSeconds());
        //  sleepLevelsMap
        sleepSummary.setSleepLevelsMap(
                this.convertSleepLevelsMap(
                        dto.getSleepLevelsMap()
                ));
        sleepSummary.setValidation(dto.getValidation());
        //  Time offset sleep respiration
        sleepSummary.setTimeOffsetSleepRespiration(
                this.convertTimeOffsetSleepRespiration(
                        dto.getTimeOffsetSleepRespiration()
                ));
        //  Time offset sleep respiration
        sleepSummary.setTimeOffsetSleepSpo2(
                this.convertTimeOffsetSleepSpo2(
                        dto.getTimeOffsetSleepSpo2()
                ));
//        //  Overall sleep score
        sleepSummary.setOverallSleepScore(
                this.convertOverallSleepScore(
                        dto.getOverallSleepScore()
                ));
        //  Overall sleep score
        sleepSummary.setSleepScores(
                this.convertSleepScores(
                        dto.getSleepScores()
                ));

        return sleepSummary;
    }

    private List<SleepScore> convertSleepScores(Map<String, Object> input) {
        if (input == null) return null;
        List<SleepScore> res = new ArrayList<>();
        for (var entry : input.entrySet()) {
            SleepScore s = new SleepScore();
            s.setQualifierKey(entry.getKey());
            var value = (LinkedHashMap<String, String>) entry.getValue();
            s.setSleepScore(value.get("qualifierKey"));
            res.add(s);
        }
        return res;
    }

    private List<TimeOffsetSleepSpo2> convertTimeOffsetSleepSpo2(Map<Integer, Integer> input) {
        if (input == null) return null;
        List<TimeOffsetSleepSpo2> res = new ArrayList<>();
        for (var entry : input.entrySet()) {
            TimeOffsetSleepSpo2 t = new TimeOffsetSleepSpo2();
            t.setKey(entry.getKey());
            t.setValue(entry.getValue());
            res.add(t);
        }
        return res;
    }

    private List<TimeOffsetSleepRespiration> convertTimeOffsetSleepRespiration(Map<Integer, Integer> input) {
        if (input == null) return null;
        List<TimeOffsetSleepRespiration> res = new ArrayList<>();
        for (var entry : input.entrySet()) {
            TimeOffsetSleepRespiration t = new TimeOffsetSleepRespiration();
            t.setKey(entry.getKey());
            t.setValue(entry.getValue());
            res.add(t);
        }
        return res;
    }

    private OverallSleepScore convertOverallSleepScore(Map<String, Object> overallSleepScoreDto) {
        if (overallSleepScoreDto == null) return null;
        OverallSleepScore overallSleepScore = new OverallSleepScore();
        overallSleepScore.setScore((Integer) overallSleepScoreDto.get("value"));
        overallSleepScore.setQualifierKey((String) overallSleepScoreDto.get("qualifierKey"));
        return overallSleepScore;
    }

    private SleepLevelsMap convertSleepLevelsMap(Map<String, List<SleepLevelTimeRange>> sleepLevelsMap) {
        if (sleepLevelsMap == null) return null;
        SleepLevelsMap res = new SleepLevelsMap();
        res.setPhaseOfSleepList(new ArrayList<>());
        List <PhaseOfSleepList> listOfPhaseList = res.getPhaseOfSleepList();

        jsonToEntitySleepLevels(sleepLevelsMap, "rem", listOfPhaseList, SleepPhaseEnum.REM);

        jsonToEntitySleepLevels(sleepLevelsMap, "deep", listOfPhaseList, SleepPhaseEnum.DEEP_SLEEP);

        jsonToEntitySleepLevels(sleepLevelsMap, "light", listOfPhaseList, SleepPhaseEnum.LIGHT_SLEEP);

        jsonToEntitySleepLevels(sleepLevelsMap, "awake", listOfPhaseList, SleepPhaseEnum.LIGHT_SLEEP);

        return res;
    }
    private void jsonToEntitySleepLevels(
            Map<String, List<SleepLevelTimeRange>> sleepLevelsMap,
            String phase,
            List<PhaseOfSleepList> listOfPhaseList,
            SleepPhaseEnum phaseEnum
    ) {
        List<SleepLevelTimeRange> curr = sleepLevelsMap.get(phase);

        PhaseOfSleepList phaseList = new PhaseOfSleepList();
        phaseList.setSleepLevelTimeRangeList(new ArrayList<>());
        listOfPhaseList.add(phaseList);
        phaseList.setSleepPhaseEnum(phaseEnum);

        if (curr == null) {
            return;
        }

        for (SleepLevelTimeRange s : curr){
            SleepLevelTimeRange sleepLevelTimeRange = new SleepLevelTimeRange();
            sleepLevelTimeRange.setStartTimeInSeconds(s.getStartTimeInSeconds());
            sleepLevelTimeRange.setEndTimeInSeconds(s.getEndTimeInSeconds());
            phaseList.getSleepLevelTimeRangeList().add(sleepLevelTimeRange);
        }
    }

    private String getCalendarDate(String calendarDate, Long startTime) {
        if (StringUtils.isNotEmpty(calendarDate)) {
            return calendarDate;
        }

        Timestamp timestamp = new Timestamp(startTime * 1000);
        return sdf.format(timestamp);
    }
}
