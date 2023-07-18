package cz.cvut.fel.vyzkumodolnosti.services.device;

import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceSleepEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.vyzkumodolnosti.repository.device.DeviceSleepEvaluationRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.ComputationUtilsService;
import cz.cvut.fel.vyzkumodolnosti.services.sleeps.SleepsService;
import cz.cvut.fel.vyzkumodolnosti.utils.TimeConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class DeviceEvaluationService {

    @Autowired
    ResearchParticipantRepository researchParticipantRepository;

    @Autowired
    DeviceSleepEvaluationRepository deviceSleepEvaluationRepository;

    @Autowired
    ComputationUtilsService compUtilsService;

    @Autowired
    SleepsService sleepsService;

    public DeviceSleepEvaluation evaluateLastWeekSleeps(ResearchParticipant researchParticipant) {

        SleepSummaryFilterDto filter = new SleepSummaryFilterDto();

        filter.setResearchIds(new HashSet<>(Collections.singleton(researchParticipant.getResearchNumber())));
        LocalDate dateFrom = LocalDate.now().minusDays(7);
        LocalDate dateTo = LocalDate.now().minusDays(0);

        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);

        List<SleepSummary> sleeps = this.sleepsService.read(filter);
        if (sleeps.size() == 0)
            return null;

        DeviceSleepEvaluation deviceSleepEvaluation = new DeviceSleepEvaluation();
        deviceSleepEvaluation.setResearchParticipant(researchParticipant);

        Predicate<AbstractMap.SimpleEntry<LocalDate, Double>> workdays = se -> se.getKey().getDayOfWeek() != DayOfWeek.SATURDAY && se.getKey().getDayOfWeek() != DayOfWeek.SUNDAY;
        Predicate<AbstractMap.SimpleEntry<LocalDate, Double>> freedays = se -> se.getKey().getDayOfWeek() == DayOfWeek.SATURDAY || se.getKey().getDayOfWeek() == DayOfWeek.SUNDAY;
        Function<SleepSummary, Double> sleepDuration = ss -> ss.getDurationInSeconds().doubleValue();
        Function<SleepSummary, Double> wakingTime = ss -> convertEpochToTimeOfDaySeconds(ss.getStartTimeInSeconds() + ss.getDurationInSeconds(), ss.getStartTimeOffsetInSeconds());
        Function<SleepSummary, Double> fallAsleepTime = ss -> setAmValuesToNextDay(convertEpochToTimeOfDaySeconds(ss.getStartTimeInSeconds(), ss.getStartTimeOffsetInSeconds()));
        Function<SleepSummary, Double> middleOfNight = ss -> setAmValuesToNextDay(convertEpochToTimeOfDaySeconds(ss.getStartTimeInSeconds() + ss.getDurationInSeconds()/2, ss.getStartTimeOffsetInSeconds()));

        deviceSleepEvaluation.setAvgSleepWorkDays(getAvgSleep(sleeps, workdays, sleepDuration));
        deviceSleepEvaluation.setAvgSleepFreeDays(getAvgSleep(sleeps, freedays, sleepDuration));
        deviceSleepEvaluation.setAvgWakingTimeWorkDays(getAvgTimeOf(sleeps, workdays, wakingTime));
        deviceSleepEvaluation.setAvgWakingTimeFreeDays(getAvgTimeOf(sleeps, freedays, wakingTime));
        deviceSleepEvaluation.setAvgFallAsleepTimeWorkDays(getAvgTimeOf(sleeps, workdays, fallAsleepTime));
        deviceSleepEvaluation.setAvgFallAsleepTimeFreeDays(getAvgTimeOf(sleeps, freedays, fallAsleepTime));
        deviceSleepEvaluation.setExpectedSleepTimeWorkDays(convertSecondsToTimeOfDaySeconds(getAvgSleep(sleeps, workdays, middleOfNight)));
        deviceSleepEvaluation.setExpectedSleepTimeFreeDays(convertSecondsToTimeOfDaySeconds(getAvgSleep(sleeps, freedays, middleOfNight)));

        return deviceSleepEvaluation;
    }

    private Double setAmValuesToNextDay(double timeOfDaySeconds) {

        return timeOfDaySeconds < 12*60*60 ? timeOfDaySeconds + 24*60*60 : timeOfDaySeconds;
    }

    private Double convertSecondsToTimeOfDaySeconds(double seconds) {
        return seconds % (60*60*24);
    }


    private Double getAvgSleep(List<SleepSummary> sleeps,
                               Predicate<AbstractMap.SimpleEntry<LocalDate, Double>> p,
                               Function<SleepSummary, Double> mapFunc
    ) {

        List<AbstractMap.SimpleEntry<LocalDate, Double>> sleepDurs = sleeps.stream()
                .map(s ->
                        new AbstractMap.SimpleEntry<>(
                                LocalDate.parse(s.getCalendarDate(), DateTimeFormatter.ISO_LOCAL_DATE),
                                mapFunc.apply(s)
                        )
                )
                .filter(p)
                .collect(Collectors.toList());

        HashMap<LocalDate, Double> means = new HashMap<>();
        HashMap<LocalDate, Integer> counts = new HashMap<>();

        for (AbstractMap.SimpleEntry<LocalDate, Double> se : sleepDurs) {
            LocalDate date = se.getKey();
            double value = se.getValue();
            if (!means.containsKey(date)) {
                means.put(date, value);
                counts.put(date, 1);
            } else {
                means.put(date, means.get(date) + value);
                counts.put(date, counts.get(date) + 1);
            }
        }

        means.replaceAll((d, v) -> means.get(d) / counts.get(d));
        return means.values().stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }


    private LocalTime getAvgTimeOf(List<SleepSummary> sleeps,
                                   Predicate<AbstractMap.SimpleEntry<LocalDate, Double>> p,
                                   Function<SleepSummary, Double> mapFunc) {

        double timeOfDaySeconds = getAvgSleep(sleeps, p, mapFunc).longValue();
        return TimeConversionUtils.secondsToLocalTime(timeOfDaySeconds);
    }

    public DeviceSleepEvaluation findNewestEvaluationClosestToDate(String researchNumber, Date created) {

        LocalDate createdDate = created.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return deviceSleepEvaluationRepository.getClosestBeforeDate(researchNumber, createdDate);
    }

    public void saveEvaluation(DeviceSleepEvaluation deviceSleepEvaluation) {
        Long version = this.deviceSleepEvaluationRepository.countByResearchParticipantResearchNumber(
                deviceSleepEvaluation.getResearchParticipant().getResearchNumber()
        ) + 1;
        deviceSleepEvaluation.setVersion(version);
        this.deviceSleepEvaluationRepository.save(deviceSleepEvaluation);
    }

    private double convertEpochToTimeOfDaySeconds(long epochSeconds, long zoneOffset) {

        ZoneId zoneId = this.getZoneIdForOffsetSeconds(zoneOffset);

        Instant instant = Instant.ofEpochSecond(epochSeconds);
        LocalTime localTime = instant.atZone(zoneId).toLocalTime();

        int hours = localTime.getHour();
        int minutes = localTime.getMinute();
        int seconds = localTime.getSecond();
        return hours * 3600 + minutes * 60 + seconds;
    }

    public ZoneId getZoneIdForOffsetSeconds(long offsetSeconds) {
        try {
            return ZoneId.ofOffset("", ZoneOffset.ofTotalSeconds((int) offsetSeconds));
        } catch (ZoneRulesException e) {
            throw new IllegalArgumentException("Invalid offset seconds: " + offsetSeconds);
        }
    }
}
