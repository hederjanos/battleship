package hu.hj.exceptions;

import hu.hj.coordinate.Coordinate;

public abstract class CoordinateException extends BattleshipException {

    protected final Coordinate coordinate;

    protected CoordinateException(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String getMessage() {
        return coordinate.toString();
    }
}