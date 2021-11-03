package hu.hj.constants;

public enum GameType {

    PVP("human versus human"), PVC("human versus computer");

    private final String name;

    private GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
