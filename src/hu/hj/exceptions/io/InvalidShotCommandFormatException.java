package hu.hj.exceptions.io;

public class InvalidShotCommandFormatException extends BattleshipIOException {

    public InvalidShotCommandFormatException(String input) {
        super(input);
    }
}
