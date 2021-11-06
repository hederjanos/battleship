package hu.hj.exceptions.coordinate;

import hu.hj.coordinate.Coordinate;

public class CoordinateAlreadySeenException extends CoordinateException {

    public CoordinateAlreadySeenException(Coordinate coordinate) {
        super(coordinate);
    }
}
