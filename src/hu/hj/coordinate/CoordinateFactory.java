package hu.hj.coordinate;

public class CoordinateFactory {

    public static Coordinate createCoordinate(int... coordinates) {
        int size = coordinates.length;
        if (size != 2) {
            throw new IllegalArgumentException();
        }
        return new Coordinate(coordinates[0], coordinates[1]);
    }
}