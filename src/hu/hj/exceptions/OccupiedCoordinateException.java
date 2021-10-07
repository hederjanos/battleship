package hu.hj.exceptions;

import hu.hj.coordinate.Coordinate;

public class OccupiedCoordinateException extends CoordinateException {

    public OccupiedCoordinateException(Coordinate coordinate) {
        super(coordinate);
    }
}
