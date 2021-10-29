package hu.hj.exceptions.coordinate;

import hu.hj.coordinate.Coordinate;
import hu.hj.exceptions.BattleshipException;

public abstract class CoordinateException extends BattleshipException {

    protected final Coordinate coordinate;

    protected CoordinateException(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName() + ": " + coordinate.toString();
    }
}