package cz.cvut.fel.vyzkumodolnosti.model.domain.computations;

public enum ChronoVsRythm {

    EARLY("Měl by chodit spát později - upravit čas vstávání", "Měl by vstávat později"),
    OK("Chodí spát ve vhodné časové okno", "Vstává ve vhodné časové okno"),
    LATER("Chodí spát později než by měl", "Vstává pozdějí než by měl");

    ChronoVsRythm(String defaultTextFa, String defaultTextWa) {
        this.defaultTextFa = defaultTextFa;
        this.defaultTextWa = defaultTextWa;
    }

    private final String defaultTextFa;
    private final String defaultTextWa;

    public String getDefaultTextFa() {
        return this.defaultTextFa;
    }
    public String getDefaultTextWa() {
        return this.defaultTextWa;
    }
}
