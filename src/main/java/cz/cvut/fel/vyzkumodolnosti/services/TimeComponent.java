package cz.cvut.fel.vyzkumodolnosti.services;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
public class TimeComponent {

    /**
     * Converts unix time to hh:mm format
     * !!!does this in system default time offset!!!
     *
     * @param unix for expamle 16622313
     * @return for example 10:12
     */
    public String unixTimeToHours(Long unix) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unix), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }

    /**
     * Converts unix time LocaLDate
     * !!!does this in system default time offset!!!
     *
     * @param unix for expamle 16622313
     * @return for example 10:12
     */
    public LocalDate unixTimeToLocaLDate(Long unix) {
        LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochSecond(unix), ZoneId.systemDefault());
        return localDate;
    }

    /**
     * Converts seconds to hh:mm format
     *
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
        if (p2 >= 24) {
            p2 -= 24;
        }
        String p2s = p2 < 10 ? "0" + p2 : String.valueOf(p2);
        String p3s = p3 < 10 ? "0" + p3 : String.valueOf(p3);
        return p2s + ":" + p3s;
    }

    /**
     * Adds trailing zero to hh mm string if the hh part is lesser than 10
     * @param hhMm hh mm String with or without trailing zero
     * @return hh mm string with trailing zero
     */
    public String addTralingZeroIfNeeded(String hhMm) {
        String[] split = hhMm.split(":");
        String s0 = split[0];
        String s1 = split[1];
        int p1 = Integer.parseInt(s0);
        if (p1 < 10) {
            StringBuilder sb = new StringBuilder(s0);
            sb.setCharAt(0, '0');
            if (s0.length() == 2) {
                sb.setCharAt(0, '0');
            } else {
                sb.append(s0);
                sb.setCharAt(0, '0');
            }
            s0 = sb.toString();
        }

        return s0 + ":" + s1;
    }

    public Integer hourMinuteFormatToSeconds(String hourMinute) {
        String[] splitted = hourMinute.split(":");
        String hoursStr = splitted[0];
        String minutesStr = splitted[1];

        int hours = Integer.parseInt(hoursStr);
        int minutes = Integer.parseInt(minutesStr);

        return (hours * 3600) + (minutes * 60);
    }

    public Double hourMinuteFormatToHours(String hourMinute) {
        String[] splitted = hourMinute.split(":");
        String hoursStr = splitted[0];
        String minutesStr = splitted[1];

        double hours = Double.parseDouble(hoursStr);
        double minutes = Double.parseDouble(minutesStr);

        return (hours) + (minutes / 60);
    }

    public Integer minutesToSeconds(Integer minutes) {
        return minutes * 60;
    }

    /**
     * @param gnt good night time in hh:mm format
     * @param gmt good morning tiem in hh:mm format
     * @return hours of difference between gnt and gmt
     */
    public Double calculateDiffBetweenGntAndGmt(String gnt, String gmt) {
        Integer gntInSeconds = this.hourMinuteFormatToSeconds(gnt);
        Integer gmtInSeconds = this.hourMinuteFormatToSeconds(gmt);

        Integer fullDayInSeconds = 86400;

        if (gntInSeconds < gmtInSeconds) {
            return this.secondsToHours(gmtInSeconds - gntInSeconds);
        }
        return secondsToHours((fullDayInSeconds - gntInSeconds) + gmtInSeconds);
    }

    /**
     * @param gnt good night time in hh:mm format
     * @param gmt good morning tiem in hh:mm format
     * @return hours of difference between gnt and gmt
     */
    public String calculateDiffBetweenGntAndGmtHhMmFormat(String gnt, String gmt) {
        Integer gntInSeconds = this.hourMinuteFormatToSeconds(gnt);
        Integer gmtInSeconds = this.hourMinuteFormatToSeconds(gmt);

        Integer fullDayInSeconds = 86400;

        if (gntInSeconds < gmtInSeconds) {
            return this.secondsToHourMinuteFormat(gmtInSeconds - gntInSeconds);
        }
        return this.secondsToHourMinuteFormat((fullDayInSeconds - gntInSeconds) + gmtInSeconds);
    }

    private Double secondsToHours(Integer seconds) {
        return seconds.doubleValue() / 3600;
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

    public long convertLocalDateToEpochSeconds(LocalDate date) {
        return date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
    }

    public LocalTime convertHhMmStringToLocalDate(String time) {
        String[] splitted = time.split(":");
        String hoursStr = splitted[0];
        String minutesStr = splitted[1];

        int hours = Integer.parseInt(hoursStr);
        int minutes = Integer.parseInt(minutesStr);

        return LocalTime.of(hours, minutes);
    }
}
