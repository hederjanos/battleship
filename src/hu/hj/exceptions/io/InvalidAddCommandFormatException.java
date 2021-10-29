package hu.hj.exceptions.io;

public class InvalidAddCommandFormatException extends BattleshipIOException {

    public InvalidAddCommandFormatException(String input) {
        super(input);
    }
}
