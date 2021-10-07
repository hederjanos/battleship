package hu.hj.exceptions;

import hu.hj.coordinate.Coordinate;

public class CoordinateAlreadyHitException extends CoordinateException {

    public CoordinateAlreadyHitException(Coordinate coordinate) {
        super(coordinate);
    }
}