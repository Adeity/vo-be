package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.handler.IllegalMeqValueException;
import cz.cvut.fel.vyzkumodolnosti.handler.IllegalTimeWindowException;
import cz.cvut.fel.vyzkumodolnosti.handler.NoSuchResearchParticipantException;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceComputationForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ComputationUtilsService {

    @Autowired
    ResearchParticipantRepository researchParticipantRepository;

    private static final String LATENCY_FA_GREATER_DEFAULT_TEXT = "Větší než";
    private static final String LATENCY_FA_LOWER_DEFAULT_TEXT = "Menší než";
    private static final String SOC_JL_GREATER_DEFAULT_TEXT = "Větší než";
    private static final String SOC_JL_LOWER_DEFAULT_TEXT = "Menší než";

    public ChronotypeEnum computeChronotypeValue(int meqVal) throws IllegalMeqValueException {
        if (meqVal >= 16 && meqVal <= 30) {
            return ChronotypeEnum.STRONGLY_EVENING;
        } else if(meqVal >= 31 && meqVal <= 41) {
            return ChronotypeEnum.WEAKLY_EVENING;
        } else if(meqVal >= 42 && meqVal <= 58) {
            return ChronotypeEnum.AMBIVALENT;
        } else if(meqVal >= 59 && meqVal <= 69) {
            return ChronotypeEnum.WEAKLY_MORNING;
        } else if (meqVal >= 70 && meqVal <= 86) {
            return ChronotypeEnum.STRONGLY_MORNING;
        } else {
            throw new IllegalMeqValueException();
        }
    }

    public ResearchParticipant getResearchParticipantByResearchNumber(String researchNumber) {
        Optional<ResearchParticipant> researchParticipant =
                this.researchParticipantRepository.findByResearchNumber(researchNumber);
        if (researchParticipant.isEmpty())
            throw new NoSuchResearchParticipantException("No research participant with research number " + researchNumber);
        return  researchParticipant.get();
    }

    public ChronoVsRythm computeChronoTypeVsRythmFa(String averageLaydownTime, LocalTime from, LocalTime to) {

        LocalTime timeValue = LocalTime.parse( averageLaydownTime, DateTimeFormatter.ISO_LOCAL_TIME );
        return this.computeChronoTypeVsRythmFa(timeValue, from, to);
    }

    public ChronoVsRythm computeChronoTypeVsRythmWa(String averageWakingTime, LocalTime from, LocalTime to) {

        LocalTime timeValue = LocalTime.parse( averageWakingTime, DateTimeFormatter.ISO_LOCAL_TIME );
        return this.computeChronoTypeVsRythmWa(timeValue, from, to);
    }
    public ChronoVsRythm computeChronoTypeVsRythmFa(LocalTime averageLaydownTime, LocalTime from, LocalTime to) {

        int laydownHours = averageLaydownTime.getHour();
        int fromHours = from.getHour();
        int toHours = to.getHour();

        if (laydownHours < 12) laydownHours += 24;
        if (fromHours < 12) fromHours += 24;
        if(toHours < 12) toHours += 24;

        if(laydownHours < fromHours) {
            return ChronoVsRythm.EARLY;
        } else if(laydownHours > toHours) {
            return ChronoVsRythm.LATER;
        } else if(laydownHours > fromHours && laydownHours < toHours) {
            return ChronoVsRythm.OK;
        } else if(laydownHours == fromHours) {
            return averageLaydownTime.getMinute() < from.getMinute() ? ChronoVsRythm.EARLY : ChronoVsRythm.OK;
        } else { // laydownHours == toHours is always true in this case
            return averageLaydownTime.getMinute() <= to.getMinute() ? ChronoVsRythm.OK : ChronoVsRythm.LATER;
        }
    }

    public ChronoVsRythm computeChronoTypeVsRythmWa(LocalTime averageWakingTime, LocalTime from, LocalTime to) {

        if(from.isAfter(to)) {
            throw new IllegalTimeWindowException();
        }

        if (averageWakingTime.isBefore(from)) {
            return ChronoVsRythm.EARLY;
        } else if(averageWakingTime.isAfter(to)) {
            return ChronoVsRythm.LATER;
        } else {
            return ChronoVsRythm.OK;
        }
    }

    public void setDefaultTexts(SleepComputationForm scfe) {

        scfe.setChronoFaText(scfe.getChronoFa().getDefaultTextFa());
        scfe.setChronoWaText(scfe.getChronoWa().getDefaultTextWa());
        scfe.setLatencyFAGreaterText(scfe.isLatencyFAGreater() ? LATENCY_FA_GREATER_DEFAULT_TEXT : LATENCY_FA_LOWER_DEFAULT_TEXT);
        scfe.setSocJetlagGreaterText(scfe.isSocJetlagGreater() ? SOC_JL_GREATER_DEFAULT_TEXT : SOC_JL_LOWER_DEFAULT_TEXT);
    }
    public void setDefaultTexts(DeviceComputationForm dcfe) {

        dcfe.setChronoFaTextWorkDays(dcfe.getChronoFaWorkDays().getDefaultTextFa());
        dcfe.setChronoWaTextWorkDays(dcfe.getChronoWaWorkDays().getDefaultTextWa());
        dcfe.setChronoFaTextFreeDays(dcfe.getChronoFaFreeDays().getDefaultTextFa());
        dcfe.setChronoWaTextFreeDays(dcfe.getChronoWaFreeDays().getDefaultTextWa());
        dcfe.setLatencyFAGreaterText(dcfe.isLatencyFAGreater() ? LATENCY_FA_GREATER_DEFAULT_TEXT : LATENCY_FA_LOWER_DEFAULT_TEXT);
        dcfe.setSocJetlagGreaterText(dcfe.isSocJetlagGreater() ? SOC_JL_GREATER_DEFAULT_TEXT : SOC_JL_LOWER_DEFAULT_TEXT);
    }

    public void updateScfeTexts(SleepComputationForm previous, SleepComputationForm recalculated) {
        if(previous.getChronoFa() == recalculated.getChronoFa())
            recalculated.setChronoFaText(previous.getChronoFaText());
        else recalculated.setChronoFaText(recalculated.getChronoFa().getDefaultTextFa());

        if(previous.getChronoWa() == recalculated.getChronoWa())
            recalculated.setChronoWaText(previous.getChronoWaText());
        else recalculated.setChronoWaText(recalculated.getChronoWa().getDefaultTextWa());

        if(previous.isLatencyFAGreater() == recalculated.isLatencyFAGreater())
            recalculated.setLatencyFAGreaterText(previous.getLatencyFAGreaterText());
        else recalculated.setLatencyFAGreaterText(recalculated.isLatencyFAGreater() ? LATENCY_FA_GREATER_DEFAULT_TEXT : LATENCY_FA_LOWER_DEFAULT_TEXT);

        if(previous.isSocJetlagGreater() == recalculated.isSocJetlagGreater())
            recalculated.setSocJetlagGreaterText(previous.getSocJetlagGreaterText());
        else recalculated.setSocJetlagGreaterText(recalculated.isSocJetlagGreater() ? SOC_JL_GREATER_DEFAULT_TEXT : SOC_JL_LOWER_DEFAULT_TEXT);
    }
    public void updateScfeTexts(DeviceComputationForm previous, DeviceComputationForm recalculated) {
        if(previous.getChronoFaWorkDays() == recalculated.getChronoFaWorkDays())
            recalculated.setChronoFaTextWorkDays(previous.getChronoFaTextWorkDays());
        else recalculated.setChronoFaTextWorkDays(recalculated.getChronoFaWorkDays().getDefaultTextFa());

        if(previous.getChronoWaWorkDays() == recalculated.getChronoWaWorkDays())
            recalculated.setChronoWaTextWorkDays(previous.getChronoWaTextWorkDays());
        else recalculated.setChronoWaTextWorkDays(recalculated.getChronoWaWorkDays().getDefaultTextWa());

        if(previous.getChronoFaFreeDays() == recalculated.getChronoFaFreeDays())
            recalculated.setChronoFaTextFreeDays(previous.getChronoFaTextFreeDays());
        else recalculated.setChronoFaTextFreeDays(recalculated.getChronoFaFreeDays().getDefaultTextFa());

        if(previous.getChronoWaFreeDays() == recalculated.getChronoWaFreeDays())
            recalculated.setChronoWaTextFreeDays(previous.getChronoWaTextFreeDays());
        else recalculated.setChronoWaTextFreeDays(recalculated.getChronoWaFreeDays().getDefaultTextWa());

        if(previous.isLatencyFAGreater() == recalculated.isLatencyFAGreater())
            recalculated.setLatencyFAGreaterText(previous.getLatencyFAGreaterText());
        else recalculated.setLatencyFAGreaterText(recalculated.isLatencyFAGreater() ? LATENCY_FA_GREATER_DEFAULT_TEXT : LATENCY_FA_LOWER_DEFAULT_TEXT);

        if(previous.isSocJetlagGreater() == recalculated.isSocJetlagGreater())
            recalculated.setSocJetlagGreaterText(previous.getSocJetlagGreaterText());
        else recalculated.setSocJetlagGreaterText(recalculated.isSocJetlagGreater() ? SOC_JL_GREATER_DEFAULT_TEXT : SOC_JL_LOWER_DEFAULT_TEXT);
    }
}
