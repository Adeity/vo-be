package cz.cvut.fel.pc2e.garminworker.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
class SleepsXlsExporterTest {
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

}