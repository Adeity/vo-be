package cz.cvut.fel.pc2e.garminworker.services;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class TimeComponent {

    /**
     * Converts unix time to hh:mm format
     * !!!does this in system default time offset!!!
     * @param unix for expamle 16622313
     * @return for example 10:12
     */
    public String unixTimeToHours(Long unix) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unix), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }

    /**
     * Converts seconds to hh:mm format
     * @param seconds - amount of seconds that must not be larger than one day
     * @return hh:mm format of seconds
     */
    public String secondsToHourMinuteFormat(Integer seconds) {
		if (seconds == null) {
			return "00:00";
		}
        int p2 = seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        String p2s = p2 < 10 ? "0" + p2 : String.valueOf(p2);
        String p3s = p3 < 10 ? "0" + p3 : String.valueOf(p3);
        return p2s + ":" + p3s;
    }

    /**
     * @param numOfDays - int value of amount of days
     * @return current time in epoch minus numOfDays in epoch
     */
    public Long getEpochBoundary(int numOfDays) {
        Long currentEpoch = Instant.now().getEpochSecond();
        Long daysInEpoch = Long.valueOf(numOfDays * 86400);
        long boundary = currentEpoch - daysInEpoch;
        return boundary;
    }

    /**
     * @param calendarDate in format YYYY-MM-DD
     * @return calendar date in format DD.MM.YYYY
     */
    public String reformatCalendarDate(String calendarDate) {
        String[] split = calendarDate.split("-");
        return split[2] + "." + split[1] + "." + split[0];
    }
}