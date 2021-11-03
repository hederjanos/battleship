package hu.hj.exceptions.io;

public class CraftAlreadyAddedException extends BattleshipIOException {

    public CraftAlreadyAddedException(String input) {
        super(input);
    }
}
