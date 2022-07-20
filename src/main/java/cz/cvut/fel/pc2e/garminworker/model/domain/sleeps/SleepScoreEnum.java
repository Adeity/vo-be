package cz.cvut.fel.pc2e.garminworker.model.domain.sleeps;

public enum SleepScoreEnum {
    TOTAL_DURATION("Total duration"), STRESS("Stress"), AWAKE_COUNT("Awake count"),
    REM_PERCENTAGE("Rem percentage"), RESTLESSNESS("Restlessness"),
    LIGHT_PERCENTAGE("Light percentage"), DEEP_PERCENTAGE("Deep percentage");

    private final String sleepScore;

    SleepScoreEnum(String sleepScore) {
        this.sleepScore = sleepScore;
    }

    public String getSleepScore() {
        return sleepScore;
    }
}
