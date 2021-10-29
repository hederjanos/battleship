package hu.hj.exceptions.io;

import hu.hj.exceptions.BattleshipException;

public class BattleshipIOException extends BattleshipException {

    protected final String input;

    public BattleshipIOException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName() + ": " + input;
    }
}
