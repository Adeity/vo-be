package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;

public class MeqComputator {
    TimeComponent timeComponent = new TimeComponent();
    public Integer calculateQ1(String q1) {
        Double hours = timeComponent.hourMinuteFormatToHours(q1);
        if (hours >= 4.45 && hours < 6.5) {
            return 5;
        } else if (hours >= 6.5 && hours < 7.75) {
            return 4;
        } else if (hours >= 7.75 && hours < 9.75) {
            return 3;
        } else if (hours >= 9.75 && hours < 11) {
            return 2;
        } else if (hours >= 11 && hours <= 12.25) {
            return 1;
        }
        return null;
    }
    public Integer calculateQ2(String q2) {
        Double hours = timeComponent.hourMinuteFormatToHours(q2);
        if (hours >= 19.75 && hours < 21) {
            return 5;
        } else if (hours >= 21 && hours < 22.25) {
            return 4;
        } else if ((hours >= 22.25 && hours < 24) || (hours >= 0 && hours < 0.5)) {
            return 3;
        } else if (hours >= 0.5 && hours < 1.75) {
            return 2;
        } else if (hours >= 1.75 && hours < 3.25) {
            return 1;
        }
        return null;
    }
    public Integer calculateQ10(String q10) {
        Double hours = timeComponent.hourMinuteFormatToHours(q10);
        if (hours >= 19.75 && hours < 21) {
            return 5;
        } else if (hours >= 21 && hours < 22.25) {
            return 4;
        } else if ((hours >= 22.25 && hours < 24) || (hours >= 0 && hours < 0.75)) {
            return 3;
        } else if (hours >= 0.75 && hours < 2) {
            return 2;
        } else if (hours >= 2 && hours < 3.25) {
            return 1;
        }
        return null;
    }
    public Integer calculateQ17(String q17) {
        Double hours = timeComponent.hourMinuteFormatToHours(q17);
        if (hours >= 0 && hours < 4) {
            return 1;
        } else if (hours >= 4 && hours < 8) {
            return 5;
        } else if (hours >= 8 && hours < 9) {
            return 4;
        } else if (hours >= 9 && hours < 14) {
            return 3;
        } else if (hours >= 14 && hours < 17) {
            return 2;
        } else if (hours >= 17 && hours < 24) {
            return 1;
        }
        return null;
    }
    public Integer calculateQ18(String q18) {
        Double hours = timeComponent.hourMinuteFormatToHours(q18);
        if (hours >= 0 && hours < 5) {
            return 1;
        } else if (hours >= 5 && hours < 8) {
            return 5;
        } else if (hours >= 8 && hours < 10) {
            return 4;
        } else if (hours >= 10 && hours < 17) {
            return 3;
        } else if (hours >= 17 && hours < 22) {
            return 2;
        } else if (hours >= 22 && hours < 24) {
            return 2;
        }
        return null;
    }
}
