package hu.hj.constants;

public enum Symbol {

    HIT('x'),
    WATER('~'),
    SEEN('•'),
    BATTLESHIP('O'),
    CRUISER('Ø'),
    CARRIER('®'),
    DESTROYER('Ω'),
    SUBMARINE('Q');

    private final char mark;

    Symbol(char mark) {
        this.mark = mark;
    }

    public final char getMark() {
        return mark;
    }
}