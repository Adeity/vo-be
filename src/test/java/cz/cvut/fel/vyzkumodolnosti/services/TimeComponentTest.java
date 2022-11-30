package cz.cvut.fel.vyzkumodolnosti.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
class TimeComponentTest {
	@InjectMocks
	TimeComponent timeComponent;

	@Test
	public void test_secondsToHours() {
		int input = 28800;
		String expectedResult = "08:00";
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

	@Test
	public void test_unixTimeToLocalDate() {
		Long input = 1652000000L;

		String expected = "2022-05-08";
		LocalDate actual = timeComponent.unixTimeToLocaLDate(input);

		assertEquals(expected, actual.toString());
	}

	@Test
	public void test_hhmmToSeconds1() {
		String input = "23:10";

		int expected = 83400;
		int actual = timeComponent.hourMinuteFormatToSeconds(input);

		assertEquals(expected, actual);
	}

	@Test
	public void test_hhmmToSeconds2() {
		String input = "01:10";

		int expected = 4200;
		int actual = timeComponent.hourMinuteFormatToSeconds(input);

		assertEquals(expected, actual);
	}

	@Test
	public void test_calculateDiffBetweenGntAndGmt1() {
		String gnt = "22:00";
		String gmt = "6:00";

		Double expected = 8.00;

		Double actual = timeComponent.calculateDiffBetweenGntAndGmt(gnt, gmt);

		assertEquals(expected, actual);
	}

	@Test
	public void test_calculateDiffBetweenGntAndGmt2() {
		String gnt = "01:30";
		String gmt = "6:00";

		Double expected = 4.50;

		Double actual = timeComponent.calculateDiffBetweenGntAndGmt(gnt, gmt);

		assertEquals(expected, actual);
	}

	@Test
	public void test_calculateDiffBetweenGntAndGmt3() {
		String gnt = "01:15";
		String gmt = "00:00";

		Double expected = 22.75;

		Double actual = timeComponent.calculateDiffBetweenGntAndGmt(gnt, gmt);

		assertEquals(expected, actual);
	}

}