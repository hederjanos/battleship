package hu.hj.coordinate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateFactoryTest {

    @Test
    void testCreateCoordinateOk() {
        Coordinate coordinate1 = new Coordinate(5, 5);
        Coordinate coordinate2 = CoordinateFactory.createCoordinate(5, 5);
        assertEquals(coordinate1, coordinate2);
        assertNotSame(coordinate1, coordinate2);
    }

    @Test
    void testCreateCoordinateWithInvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateFactory.createCoordinate(5));
    }
}