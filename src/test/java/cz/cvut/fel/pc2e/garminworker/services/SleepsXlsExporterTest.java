package cz.cvut.fel.pc2e.garminworker.services;

import cz.cvut.fel.pc2e.garminworker.services.sleeps.SleepsXlsExporter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test.properties")
class SleepsXlsExporterTest {
    @Autowired
    SleepsXlsExporter SleepsXlsExporter;
    @Autowired
    TimeComponent timeComponent;
    @Test
    public void test_secondsToHours() {
        int input = 28800;
        String expectedResult = "08:00:00";
        String actual = timeComponent.secondsToHourMinuteFormat(input);

        System.out.printf("Expected: %s, actual: %s", expectedResult, actual);

        assertEquals(expectedResult, actual);
    }

    @Test
    public void test_unixTimeToHours() {
        Long input = 1652000000L;

        String expected = "10:53";
        String actual = timeComponent.unixTimeToHours(input);

        assertEquals(expected, actual);
    }


}