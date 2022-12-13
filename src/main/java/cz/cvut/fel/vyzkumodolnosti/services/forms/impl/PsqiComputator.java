package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;
import org.apache.commons.math3.util.Precision;

public class PsqiComputator {
    private TimeComponent timeComponent = new TimeComponent();

    public int calculatePsqiDurat(Double q4) {
        if (q4 >= 7) {
            return 0;
        } else if (q4 < 7 && q4 >= 6) {
            return 1;
        } else if (q4 < 6 && q4 >= 5) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculatePsqidistb(int q5bScore,
                                   int q5cScore,
                                   int q5dScore,
                                   int q5eScore,
                                   int q5fScore,
                                   int q5gScore,
                                   int q5hScore,
                                   int q5iScore,
                                   int q5jScore) {
        int sum = q5bScore + q5cScore + q5dScore + q5eScore + q5fScore + q5gScore + q5hScore + q5iScore + q5jScore;
        if (sum == 0) {
            return 0;
        } else if (sum >= 1 && sum <= 9) {
            return 1;
        } else if (sum > 9 && sum <= 18) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculatePsqilaten(int q2, int q5aScore) {
        int q2New;
        if (q2 >= 0 && q2 <= 15) {
            q2New = 0;
        } else if (q2 > 15 && q2 <= 30) {
            q2New = 1;
        } else {
            q2New = 3;
        }
        int sum = q2New + q5aScore;
        if (sum == 0) {
            return 0;
        } else if (sum == 1 || sum == 2) {
            return 1;
        } else if (sum == 3 || sum == 4) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculatePsqidaydys(int q8Score, int q9Score) {
        int sum = q8Score + q9Score;
        if (sum == 0) {
            return 0;
        } else if (sum == 1 || sum == 2) {
            return 1;
        } else if (sum == 3 || sum == 4) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculatePsqihse(String q1HhMm, String q3HhMm, Double q4) {
        Double efficiency = calculateSleepEfficiency(q1HhMm, q3HhMm, q4);
        if (efficiency >= 85) {
            return 0;
        } else if (efficiency < 85 && efficiency >= 75) {
            return 1;
        } else if (efficiency < 75 && efficiency >= 65) {
            return 2;
        } else {
            return 3;
        }
    }

    public Double calculateSleepEfficiency(String gnt, String gmt, Double actualHoursOfSleep) {
        Double hoursBetweneGntGmt = timeComponent.calculateDiffBetweenGntAndGmt(gnt, gmt);
        return Precision.round((actualHoursOfSleep / hoursBetweneGntGmt) * 100, 2);
    }
}
