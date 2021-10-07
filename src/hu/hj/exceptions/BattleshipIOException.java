package hu.hj.exceptions;

public class BattleshipIOException extends BattleshipException {

    private String message;

    protected BattleshipIOException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
