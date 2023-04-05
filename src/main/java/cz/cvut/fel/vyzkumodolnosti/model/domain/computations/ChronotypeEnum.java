package cz.cvut.fel.vyzkumodolnosti.model.domain.computations;

public enum ChronotypeEnum {

    STRONGLY_MORNING("Výrazně ranní typ", 0),
    WEAKLY_MORNING("Spíše ranní typ", 1),
    AMBIVALENT("Nevyhraněný typ", 2),
    WEAKLY_EVENING("Spíše večerní typ", 3),
    STRONGLY_EVENING("Výrazně večerní typ", 4);

    private final String title;

    private final int id;

    ChronotypeEnum(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
