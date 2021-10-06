package hu.hj.coordinate;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testConstructorWithInvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinate(null));
    }

    @Test
    void testCopy() {
        Coordinate coordinate = new Coordinate(2, 2);
        Coordinate copy = coordinate.copy();
        assertEquals(coordinate, copy);
        assertNotSame(coordinate, copy);
    }

    @Test
    void testAddOk() {
        Coordinate coordinate = new Coordinate(2, 2);
        Coordinate addedCoordinate = coordinate.add(new Coordinate(1, 2));
        assertNotSame(coordinate, addedCoordinate);
        assertEquals(3, addedCoordinate.getX());
        assertEquals(4, addedCoordinate.getY());
    }

    @Test
    void testAddWithInvalidArgument() {
        Coordinate coordinate = new Coordinate(2, 2);
        assertThrows(IllegalArgumentException.class, () -> coordinate.add(null));
    }

    @Test
    void testSubtractOk() {
        Coordinate coordinate = new Coordinate(2, 2);
        Coordinate subtractedCoordinate = coordinate.subtract(new Coordinate(3, 1));
        assertNotSame(coordinate, subtractedCoordinate);
        assertEquals(-1, subtractedCoordinate.getX());
        assertEquals(1, subtractedCoordinate.getY());
    }

    @Test
    void testSubtractWithInvalidArgument() {
        Coordinate coordinate = new Coordinate(2, 2);
        assertThrows(IllegalArgumentException.class, () -> coordinate.subtract(null));
    }

    @Test
    void testGetAdjacentCoordinates() {
        Coordinate coordinate = new Coordinate(3, 3);
        Set<Coordinate> adjacentCoordinates = coordinate.getAdjacentCoordinates();
        for (int i = 2; i <= 4; i++) {
            for (int j = 2; j <= 4; j++) {
                if (i == coordinate.getX() && i == j) {
                    assertFalse(adjacentCoordinates.contains(new Coordinate(i, j)));
                } else {
                    assertTrue(adjacentCoordinates.contains(new Coordinate(i, j)));
                }
            }
        }
    }

    @Test
    void testToString() {
        Coordinate coordinate = new Coordinate(8, 9);
        assertEquals("(8, 9)", coordinate.toString());
    }
}