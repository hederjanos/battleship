package hu.hj.exceptions.io;

public class InvalidMenuPoint extends BattleshipIOException {

    public InvalidMenuPoint() {
    }

    public InvalidMenuPoint(String input) {
        super(input);
    }
}
