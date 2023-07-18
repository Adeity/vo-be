package cz.cvut.fel.vyzkumodolnosti.utils;

import java.time.LocalTime;

public class TimeConversionUtils {

    public static LocalTime hhMmToLocalTime(String hhmm) {
        int hours = Integer.parseInt(hhmm.substring(0, 2));
        int minutes = Integer.parseInt(hhmm.substring(3, 5));

        return LocalTime.of(hours, minutes);
    }

    public static String localTimeToHhMm(LocalTime localTime) {
        String hours = String.format("%02d", localTime.getHour());
        String minutes = String.format("%02d", localTime.getMinute());

        return hours + ":" + minutes;
    }

    public static String sjlFromDoubleToString(Double seconds) {
        int hours = (int) (seconds / 3600);
        int minutes = (int) Math.abs(((seconds % 3600) / 60));

        return String.format("%02d:%02d", hours, minutes);
    }

    public static LocalTime secondsToLocalTime(Double seconds) {
        String hhMm = TimeConversionUtils.sjlFromDoubleToString(seconds);
        return TimeConversionUtils.hhMmToLocalTime(hhMm);
    }
}
