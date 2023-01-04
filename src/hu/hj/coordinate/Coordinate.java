package hu.hj.coordinate;

import hu.hj.constants.Direction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Coordinate implements Serializable {

    private static final long serialVersionUID = 1L;

    private int x;
    private int y;

    protected Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Coordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException();
        }
        setX(coordinate.getX());
        setY(coordinate.getY());
    }

    public Coordinate copy() {
        return new Coordinate(this);
    }

    public Coordinate add(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException();
        }
        Coordinate newCoordinate = copy();
        newCoordinate.setX(newCoordinate.getX() + coordinate.getX());
        newCoordinate.setY(newCoordinate.getY() + coordinate.getY());
        return newCoordinate;
    }

    public Coordinate subtract(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException();
        }
        Coordinate newCoordinate = copy();
        newCoordinate.setX(newCoordinate.getX() - coordinate.getX());
        newCoordinate.setY(newCoordinate.getY() - coordinate.getY());
        return newCoordinate;
    }

    public Set<Coordinate> getAdjacentCoordinates() {
        Set<Coordinate> adjacentCoordinates = new HashSet<>();
        Coordinate neighborCoordinate;
        for (Direction direction : Direction.values()) {
            neighborCoordinate = copy();
            neighborCoordinate.setX(neighborCoordinate.getX() + direction.getX());
            neighborCoordinate.setY(neighborCoordinate.getY() + direction.getY());
            adjacentCoordinates.add(neighborCoordinate);
        }
        return adjacentCoordinates;
    }

    public Set<Coordinate> getOrthogonalAdjacentCoordinates() {
        Set<Coordinate> adjacentCoordinates = new HashSet<>();
        Coordinate neighborCoordinate;
        Direction[] directions = Direction.values();
        for (int i = 0; i < 4; i++) {
            neighborCoordinate = copy();
            neighborCoordinate.setX(neighborCoordinate.getX() + directions[i].getX());
            neighborCoordinate.setY(neighborCoordinate.getY() + directions[i].getY());
            adjacentCoordinates.add(neighborCoordinate);
        }
        return adjacentCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%s, %d)", (char) (x + 'a'), y + 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }
}