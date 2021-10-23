package hu.hj.craft;

import hu.hj.constants.Orientation;
import hu.hj.craft.crafts.CraftUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class CraftUtilitiesTest {

    private int[][] shape;

    @BeforeEach
    void setUp() {
        shape = new int[][]{
                {0, 0, 2, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}
        };
    }

    @Test
    void testGetNorthOrientation() {
        int[][] expected =
                {
                        {0, 0, 2, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                };
        int[][] rotatedShape = CraftUtilities.getOrientedShape(shape, Orientation.NORTH);
        assertArrayEquals(expected, rotatedShape);
        assertNotSame(shape, rotatedShape);
    }

    @Test
    void testGetEastOrientation() {
        int[][] expected =
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 2},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                };
        int[][] rotatedShape = CraftUtilities.getOrientedShape(shape, Orientation.EAST);
        assertArrayEquals(expected, rotatedShape);
        assertNotSame(shape, rotatedShape);
    }

    @Test
    void testGetSouthOrientation() {
        int[][] expected =
                {
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 2, 0, 0}
                };
        int[][] rotatedShape = CraftUtilities.getOrientedShape(shape, Orientation.SOUTH);
        assertArrayEquals(expected, rotatedShape);
        assertNotSame(shape, rotatedShape);
    }

    @Test
    void testGetWestOrientation() {
        int[][] expected =
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {2, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                };
        int[][] rotatedShape = CraftUtilities.getOrientedShape(shape, Orientation.WEST);
        assertArrayEquals(expected, rotatedShape);
        assertNotSame(shape, rotatedShape);
    }

    @Test
    void testSearchPositionInShape() {
        int[] expectedPosition = {2, 0};
        int[] currentPosition = CraftUtilities.searchPositionInShape(shape, 2);
        assertArrayEquals(expectedPosition, currentPosition);
    }
}