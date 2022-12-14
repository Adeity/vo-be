package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;

public class MctqComputator {
    private final TimeComponent timeComponent = new TimeComponent();

    public String calculateSOw(String SPrepw, Integer SLatw) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(SPrepw) +
                        timeComponent.minutesToSeconds(SLatw));
    }

    public String calculateGUw(String SEw, Integer SIw) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(SEw) +
                        timeComponent.minutesToSeconds(SIw));
    }

    public String calculateSDw(String SEw, String SOw) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(SEw) -
                        timeComponent.hourMinuteFormatToSeconds(SOw));
    }

    public String calculateTBTw(String GUw, String BTw) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(GUw) -
                        timeComponent.hourMinuteFormatToSeconds(BTw));
    }

    public String calculateMSW(String GUw, String SDw) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(GUw) -
                        (timeComponent.hourMinuteFormatToSeconds(SDw) / 2));
    }

    public String calculateSOf(String SPrepf, Integer SLatf) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(SPrepf) +
                        timeComponent.minutesToSeconds(SLatf));
    }

    public String calculateGUf(String SEf, Integer SIf) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(SEf) +
                        timeComponent.minutesToSeconds(SIf));
    }

    public String calculateSDf(String SEf, String SOf) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(SEf) -
                        timeComponent.hourMinuteFormatToSeconds(SOf));
    }

    public String calculateTBTf(String GUf, String BTf) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(GUf) -
                        timeComponent.hourMinuteFormatToSeconds(BTf));
    }

    public String calculateMSF(String GUf, String SDf) {
        return timeComponent.secondsToHourMinuteFormat(
                timeComponent.hourMinuteFormatToSeconds(GUf) -
                        (timeComponent.hourMinuteFormatToSeconds(SDf) / 2));
    }

    public String calculateSDweek(String SDw, Integer WD, String SDf, Integer FD) {
        Integer SDwseconds = timeComponent.hourMinuteFormatToSeconds(SDw);
        Integer SDfseconds = timeComponent.hourMinuteFormatToSeconds(SDf);
        Integer resSeconds = ((SDwseconds * WD) * (SDfseconds + FD)) / 7;
        return timeComponent.secondsToHourMinuteFormat(resSeconds);
    }

    public String calculateMSFsc(Boolean alarmf, String SDf, String SDw, String MSF, String SDweek) {
        if (Boolean.TRUE.equals(alarmf)) {
            return null;
        }

        Integer SDfseconds = timeComponent.hourMinuteFormatToSeconds(SDf);
        Integer SDwseconds = timeComponent.hourMinuteFormatToSeconds(SDw);
        if (SDfseconds <= SDwseconds) {
            return MSF;
        }

        Integer MSFseconds = timeComponent.hourMinuteFormatToSeconds(MSF);
        Integer SDweeksecnods = timeComponent.hourMinuteFormatToSeconds(SDweek);

        Integer resSeconds = MSFseconds - (SDfseconds - SDweeksecnods) / 2;

        return timeComponent.secondsToHourMinuteFormat(resSeconds);
    }

    public String calculateSLossweek(String SDweek, String SDw, String SDf, Integer WD, Integer FD) {
        Integer SDweekSeconds = timeComponent.hourMinuteFormatToSeconds(SDweek);
        Integer SDwSeconds = timeComponent.hourMinuteFormatToSeconds(SDw);
        Integer SDfSeconds = timeComponent.hourMinuteFormatToSeconds(SDf);

        int resSeconds;

        if (SDweekSeconds > SDwSeconds) {
            resSeconds = (SDweekSeconds - SDwSeconds) * WD;
            return timeComponent.secondsToHourMinuteFormat(resSeconds);
        }

        resSeconds = (SDweekSeconds - SDfSeconds) * FD;
        return timeComponent.secondsToHourMinuteFormat(resSeconds);
    }

    public String calculateSJLrel(String MSF, String MSW) {
        int resSeconds = timeComponent.hourMinuteFormatToSeconds(MSF) - timeComponent.hourMinuteFormatToSeconds(MSW);
        return timeComponent.secondsToHourMinuteFormat(resSeconds);
    }

    public String calculateSJL(String MSF, String MSW) {
        int resSeconds = Math.abs(timeComponent.hourMinuteFormatToSeconds(MSF) - timeComponent.hourMinuteFormatToSeconds(MSW));
        return timeComponent.secondsToHourMinuteFormat(resSeconds);
    }

    public String calculateLEweek(String LEw, Integer WD, Integer FD, String LEf) {
        Integer LEwseconds = timeComponent.hourMinuteFormatToSeconds(LEw);
        Integer LEfseconds = timeComponent.hourMinuteFormatToSeconds(LEf);

        int resSeconds = ((LEwseconds * WD)  + (LEfseconds * FD)) / 7;

        return timeComponent.secondsToHourMinuteFormat(resSeconds);
    }

}
