package hu.hj.exceptions.io;

import hu.hj.exceptions.BattleshipException;

public abstract class BattleshipIOException extends BattleshipException {

    protected String input;

    protected BattleshipIOException() {
    }

    protected BattleshipIOException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName() + ": " + input;
    }
}
