package hu.hj.exceptions.io;

public class InvalidCraftNameException extends BattleshipIOException {

    public InvalidCraftNameException(String input) {
        super(input);
    }
}
