package hu.hj.exceptions;

import hu.hj.coordinate.Coordinate;

public class NextToAnotherException extends CoordinateException {

    public NextToAnotherException(Coordinate coordinate) {
        super(coordinate);
    }
}
