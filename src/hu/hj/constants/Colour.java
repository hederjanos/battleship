package hu.hj.constants;

public enum Colour {

    ANSI_RESET("\u001B[0m"),

    ANSI_BLACK("\u001B[30m"),

    ANSI_RED("\u001B[31m"),

    ANSI_GREEN("\u001B[32m"),

    ANSI_YELLOW("\u001b[33m"),

    ANSI_BLUE("\u001B[34m"),

    ANSI_PURPLE("\u001B[35m"),

    ANSI_CYAN("\u001B[36m"),

    ANSI_WHITE("\u001b[37m");

    private final String colourCode;

    private Colour(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getColourCode() {
        return colourCode;
    }
}