package hu.hj.craft;

import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.ships.Carrier;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CraftTest {

    private Carrier carrier;

    @Test
    void testGetAbsolutPositions() {
        carrier = new Carrier(Orientation.NORTH);
        assertThrows(IllegalArgumentException.class, () -> carrier.getAbsoluteCoordinates(null));
        assertNull(carrier.getAnchorCoordinate());
    }

    @Test
    void testGetAbsolutPositionsInBound() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        Set<Coordinate> expectedCoordinates = new HashSet<>();
        expectedCoordinates.add(CoordinateFactory.createCoordinate(2, 2));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(2, 3));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(2, 4));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(2, 5));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(2, 6));
        assertEquals(expectedCoordinates, carrier.getAbsoluteCoordinates(anchorCoordinate));
    }

    @Test
    void testGetAbsolutPositionsOutOfBound() {
        carrier = new Carrier(Orientation.EAST);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        Set<Coordinate> expectedCoordinates = new HashSet<>();
        expectedCoordinates.add(CoordinateFactory.createCoordinate(2, 2));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(1, 2));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(0, 2));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(-1, 2));
        expectedCoordinates.add(CoordinateFactory.createCoordinate(-2, 2));
        assertEquals(expectedCoordinates, carrier.getAbsoluteCoordinates(anchorCoordinate));
    }

    @Test
    void testGetAnchorCoordinate() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertEquals(anchorCoordinate, carrier.getAnchorCoordinate());
        assertNotSame(anchorCoordinate, carrier.getAnchorCoordinate());
    }

    @Test
    void testHitOk() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));

        int[][] expectedShape =
                {
                        {0, 0, 2, 0, 0},
                        {0, 0, -1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                };
        assertArrayEquals(expectedShape, carrier.getShape());
    }

    @Test
    void testHitOkMultipleShot() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 4)));
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 5)));
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 6)));
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 2)));

        int[][] expectedShape =
                {
                        {0, 0, -1, 0, 0},
                        {0, 0, -1, 0, 0},
                        {0, 0, -1, 0, 0},
                        {0, 0, -1, 0, 0},
                        {0, 0, -1, 0, 0}
                };
        assertArrayEquals(expectedShape, carrier.getShape());
    }

    @Test
    void testHitAlreadyHit() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));
        assertFalse(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));

        int[][] expectedShape =
                {
                        {0, 0, 2, 0, 0},
                        {0, 0, -1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                };
        assertArrayEquals(expectedShape, carrier.getShape());
    }

    @Test
    void testHitFail() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertFalse(carrier.hit(CoordinateFactory.createCoordinate(1, 1)));

        int[][] expectedShape =
                {
                        {0, 0, 2, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                };
        assertArrayEquals(expectedShape, carrier.getShape());
    }

    @Test
    void testIsHitYes() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        carrier.hit(CoordinateFactory.createCoordinate(2, 5));
        assertTrue(carrier.isHit(CoordinateFactory.createCoordinate(2, 5)));
    }

    @Test
    void testIsHitNo() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertFalse(carrier.isHit(anchorCoordinate));
    }

    @Test
    void testIsShotDownYes() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        carrier.hit(CoordinateFactory.createCoordinate(2, 2));
        carrier.hit(CoordinateFactory.createCoordinate(2, 3));
        carrier.hit(CoordinateFactory.createCoordinate(2, 4));
        carrier.hit(CoordinateFactory.createCoordinate(2, 5));
        carrier.hit(CoordinateFactory.createCoordinate(2, 6));
        assertTrue(carrier.isShotDown());
    }

    @Test
    void testIsShotDownNo() {
        carrier = new Carrier(Orientation.NORTH);
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        carrier.hit(CoordinateFactory.createCoordinate(2, 2));
        carrier.hit(CoordinateFactory.createCoordinate(2, 3));
        carrier.hit(CoordinateFactory.createCoordinate(2, 4));
        carrier.hit(CoordinateFactory.createCoordinate(2, 5));
        assertFalse(carrier.isShotDown());
    }
}