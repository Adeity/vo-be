package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.handler.IllegalMeqValueException;
import cz.cvut.fel.vyzkumodolnosti.handler.IllegalTimeWindowException;
import cz.cvut.fel.vyzkumodolnosti.handler.NoSuchResearchParticipantException;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComputationUtilsServiceTest {

    @Mock
    ResearchParticipantRepository researchParticipantRepository;
    @InjectMocks
    ComputationUtilsService computationUtilsService;

    @BeforeEach
    public void setup() {
    }

    @ParameterizedTest
    @CsvSource({
            "16, STRONGLY_EVENING",
            "30, STRONGLY_EVENING",
            "31, WEAKLY_EVENING",
            "41, WEAKLY_EVENING",
            "42, AMBIVALENT",
            "58, AMBIVALENT",
            "59, WEAKLY_MORNING",
            "69, WEAKLY_MORNING",
            "70, STRONGLY_MORNING",
            "86, STRONGLY_MORNING"
    })
    void computeChronotypeValue(int meqValue, ChronotypeEnum expectedVal) {

        var chronoVal = computationUtilsService.computeChronotypeValue(meqValue);

        assertEquals(chronoVal, expectedVal);
    }

    @ParameterizedTest
    @CsvSource({"-2147483648", "15", "87, 2147483647"})
    void computeChronotypeValue(int meqValue) {

        assertThrows(IllegalMeqValueException.class, () -> computationUtilsService.computeChronotypeValue(meqValue));
    }

    @Test
    void getResearchParticipantByResearchNumberExists() {
        final var researchNumber = "res_100";
        when(researchParticipantRepository.findByResearchNumber(researchNumber)).thenReturn(Optional.of(new ResearchParticipant()));

        var researchParticipant = this.computationUtilsService.getResearchParticipantByResearchNumber(researchNumber);

        verify(this.researchParticipantRepository, times(1)).findByResearchNumber(Mockito.matches(researchNumber));

        assertNotNull(researchParticipant);
    }

    @Test
    void getResearchParticipantByResearchNumberNull() {
        final var researchNumber = "res_100";
        when(researchParticipantRepository.findByResearchNumber(researchNumber)).thenReturn(Optional.empty());

        assertThrows(NoSuchResearchParticipantException.class, () -> {
            this.computationUtilsService.getResearchParticipantByResearchNumber(researchNumber);
        });
        verify(this.researchParticipantRepository, times(1)).findByResearchNumber(Mockito.matches(researchNumber));
    }

    @Test
    void computeChronoTypeVsRythmFa() {
    }

    @ParameterizedTest
    @CsvSource({
            "00:00, 12:00, 12:30, EARLY",
            "11:59, 12:00, 12:30, EARLY",
            "12:00, 12:00, 12:30, OK",
            "12:30, 12:00, 12:30, OK",
            "12:31, 12:00, 12:30, LATER",
            "23:59, 12:00, 12:30, LATER",
    })
    void computeChronoTypeVsRythmWa(LocalTime time, LocalTime from, LocalTime to, ChronoVsRythm timeVsWindow) {

        var result= this.computationUtilsService.computeChronoTypeVsRythmWa(time, from, to);
        assertEquals(result, timeVsWindow);
    }

    @Test
    void computeChronoTypeVsRythmWaException() {

        LocalTime time = LocalTime.of(12, 30);
        LocalTime from = LocalTime.of(12, 31);
        LocalTime to = LocalTime.of(12, 30);

        assertThrows(IllegalTimeWindowException.class, () -> {
            this.computationUtilsService.computeChronoTypeVsRythmWa(time, from, to);
        });
    }

}