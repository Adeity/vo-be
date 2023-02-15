package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PsqiComputatorTest {
    private final PsqiComputator computator = new PsqiComputator();

    @Test
    public void test_sleepEfficiency_1() {
        String gnt = "22:15";
        String gmt = "6:30";
        Double actualHoursOfSleep = 7.00;

        Double expected = 84.85;
        Double actual = computator.calculateSleepEfficiency(gnt, gmt, actualHoursOfSleep);

        assertEquals(expected, actual);
    }

    @Test
    public void test_sleepEfficiency_2() {
        String gnt = "22:30";
        String gmt = "6:30";
        Double actualHoursOfSleep = 5.5;

        Double expected = 68.75;
        Double actual = computator.calculateSleepEfficiency(gnt, gmt, actualHoursOfSleep);

        assertEquals(expected, actual);
    }

    @Test
    public void test_sleepEfficiency_3() {
        String gnt = "22:15";
        String gmt = "7:00";
        Double actualHoursOfSleep = 7.00;

        Double expected = 80.00;
        Double actual = computator.calculateSleepEfficiency(gnt, gmt, actualHoursOfSleep);

        assertEquals(expected, actual);
    }

    @Test
    public void test_sleepEfficiency_4() {
        String gnt = "22:00";
        String gmt = "5:30";
        Double actualHoursOfSleep = 7.50;

        Double expected = 100.00;
        Double actual = computator.calculateSleepEfficiency(gnt, gmt, actualHoursOfSleep);

        assertEquals(expected, actual);
    }

}