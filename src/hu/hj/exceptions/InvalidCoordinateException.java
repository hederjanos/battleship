package hu.hj.exceptions;

import hu.hj.coordinate.Coordinate;

public class InvalidCoordinateException extends CoordinateException {

    public InvalidCoordinateException(Coordinate coordinate) {
        super(coordinate);
    }
}