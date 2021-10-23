package hu.hj.craft;

import hu.hj.constants.Orientation;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.ship.Carrier;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CraftTest {

    private Craft carrier;

    @BeforeEach
    void setUp() {
        carrier = new Carrier();
        carrier.setOrientation(Orientation.NORTH);
    }

    @Test
    void testConstruction() {
        for (Orientation orientation : Orientation.values()) {
            carrier = new Carrier();
            carrier.setOrientation(orientation);
            switch (orientation) {
                case SOUTH:
                    assertEquals(Orientation.SOUTH, carrier.getOrientation());
                    assertEquals(CoordinateFactory.createCoordinate(2, 4), carrier.getInternalAnchorCoordinate());
                    break;
                case NORTH:
                    assertEquals(Orientation.NORTH, carrier.getOrientation());
                    assertEquals(CoordinateFactory.createCoordinate(2, 0), carrier.getInternalAnchorCoordinate());
                    break;
                case EAST:
                    assertEquals(Orientation.EAST, carrier.getOrientation());
                    assertEquals(CoordinateFactory.createCoordinate(4, 2), carrier.getInternalAnchorCoordinate());
                    break;
                case WEST:
                    assertEquals(Orientation.WEST, carrier.getOrientation());
                    assertEquals(CoordinateFactory.createCoordinate(0, 2), carrier.getInternalAnchorCoordinate());
                    break;
            }
        }
    }

    @Test
    void testGetAbsolutPositions() {
        assertThrows(IllegalArgumentException.class, () -> carrier.getAbsoluteCoordinates(null));
        assertNull(carrier.getAnchorCoordinate());
    }

    @Test
    void testGetAbsolutPositionsInBound() {
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
        carrier = new Carrier();
        carrier.setOrientation(Orientation.EAST);
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
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertEquals(anchorCoordinate, carrier.getAnchorCoordinate());
        assertNotSame(anchorCoordinate, carrier.getAnchorCoordinate());
    }

    @Test
    void testHitOk() {
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }

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
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 4)));
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 5)));
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 6)));
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 2)));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }

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
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            assertTrue(carrier.hit(CoordinateFactory.createCoordinate(2, 3)));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }
        assertThrows(CoordinateAlreadyHitException.class, () -> carrier.hit(CoordinateFactory.createCoordinate(2, 3)));

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
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            assertFalse(carrier.hit(CoordinateFactory.createCoordinate(1, 1)));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }

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
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            carrier.hit(CoordinateFactory.createCoordinate(2, 5));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }
        assertTrue(carrier.isHit(CoordinateFactory.createCoordinate(2, 5)));
    }

    @Test
    void testIsHitNo() {
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        assertFalse(carrier.isHit(anchorCoordinate));
    }

    @Test
    void testIsShotDownYes() {
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            carrier.hit(CoordinateFactory.createCoordinate(2, 2));
            carrier.hit(CoordinateFactory.createCoordinate(2, 3));
            carrier.hit(CoordinateFactory.createCoordinate(2, 4));
            carrier.hit(CoordinateFactory.createCoordinate(2, 5));
            carrier.hit(CoordinateFactory.createCoordinate(2, 6));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }
        assertTrue(carrier.isShotDown());
    }

    @Test
    void testIsShotDownNo() {
        Coordinate anchorCoordinate = CoordinateFactory.createCoordinate(2, 2);
        carrier.setAnchorCoordinate(anchorCoordinate);
        try {
            carrier.hit(CoordinateFactory.createCoordinate(2, 2));
            carrier.hit(CoordinateFactory.createCoordinate(2, 3));
            carrier.hit(CoordinateFactory.createCoordinate(2, 4));
            carrier.hit(CoordinateFactory.createCoordinate(2, 5));
        } catch (CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }
        assertFalse(carrier.isShotDown());
    }
}