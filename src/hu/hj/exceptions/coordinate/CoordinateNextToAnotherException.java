package hu.hj.exceptions.coordinate;

import hu.hj.coordinate.Coordinate;

public class CoordinateNextToAnotherException extends CoordinateException {

    public CoordinateNextToAnotherException(Coordinate coordinate) {
        super(coordinate);
    }
}
