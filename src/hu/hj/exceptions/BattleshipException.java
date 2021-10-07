package hu.hj.exceptions;

public abstract class BattleshipException extends Exception {

    protected static final long serialVersionUID = 1L;

    protected BattleshipException() {
    }

    protected BattleshipException(String message) {
        super(message);
    }
}