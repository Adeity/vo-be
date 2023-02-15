package cz.cvut.fel.vyzkumodolnosti.model.domain.sleeps;

public enum SleepPhaseEnum {
    REM("REM"), DEEP_SLEEP("Deep sleep"), LIGHT_SLEEP("Light sleep");

    private final String sleepPhase;

    SleepPhaseEnum(String sleepPhase) {
        this.sleepPhase = sleepPhase;
    }

    public String getSleepPhase() {
        return sleepPhase;
    }
}

