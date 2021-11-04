package hu.hj.board;

import hu.hj.constants.Orientation;
import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.craft.crafts.diveable.Submarine;
import hu.hj.craft.crafts.ship.Battleship;
import hu.hj.craft.crafts.ship.Carrier;
import hu.hj.craft.crafts.ship.Cruiser;
import hu.hj.craft.crafts.ship.Destroyer;
import hu.hj.exceptions.coordinate.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBoardTest {

    private Board board;
    private Craft carrier;
    private Craft cruiser;
    private Craft destroyer;
    private Craft battleship;
    private Craft submarine;

    @BeforeEach
    void setUp() {
        board = new SimpleBoard();

        carrier = new Carrier();
        carrier.setOrientation(Orientation.NORTH);

        cruiser = new Cruiser();
        cruiser.setOrientation(Orientation.SOUTH);

        destroyer = new Destroyer();
        destroyer.setOrientation(Orientation.NORTH);

        battleship = new Battleship();
        battleship.setOrientation(Orientation.EAST);

        submarine = new Submarine();
        submarine.setOrientation(Orientation.WEST);
    }

    @Test
    void testConstructorOk1() {
        assertEquals(10, board.getSize());
    }

    @Test
    void testConstructorOk2() {
        Board board2 = new SimpleBoard(15);
        assertEquals(15, board2.getSize());
    }

    @Test
    void testConstructorNotOk() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleBoard(4));
        assertThrows(IllegalArgumentException.class, () -> new SimpleBoard(22));
    }

    @Test
    void testAddOneCraftInBound() {
        String expectedBoard =
                "~~~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~~~~~~";
        Coordinate coordinate = CoordinateFactory.createCoordinate(1, 2);
        try {
            board.addCraft(carrier, coordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertNotEquals(carrier, board.getCraft(CoordinateFactory.createCoordinate(1, 1)));
        assertEquals(carrier, board.getCraft(coordinate));
        assertEquals(carrier, board.getCraft(CoordinateFactory.createCoordinate(1, 3)));
        assertEquals(carrier, board.getCraft(CoordinateFactory.createCoordinate(1, 4)));
        assertEquals(carrier, board.getCraft(CoordinateFactory.createCoordinate(1, 5)));
        assertEquals(carrier, board.getCraft(CoordinateFactory.createCoordinate(1, 6)));
        assertNotEquals(carrier, board.getCraft(CoordinateFactory.createCoordinate(1, 7)));
        assertEquals(expectedBoard, board.toString(true));
    }

    @Test
    void testAddOneCraftOutOfBound() {
        Coordinate coordinate = CoordinateFactory.createCoordinate(1, 11);
        assertThrows(InvalidCoordinateException.class, () -> board.addCraft(carrier, coordinate));

    }

    @Test
    void testAddOneCraftOutOfBound2() {
        Coordinate coordinate = CoordinateFactory.createCoordinate(-2, 8);
        assertThrows(InvalidCoordinateException.class, () -> board.addCraft(carrier, coordinate));

    }

    @Test
    void testAddOneCraftToOccupiedCoordinate() {
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        try {
            board.addCraft(carrier, carrierCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(2, 4);
        assertThrows(OccupiedCoordinateException.class, () -> board.addCraft(battleship, battleshipCoordinate));
    }

    @Test
    void testAddOneCraftNextToAnotherCraft() {
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        try {
            board.addCraft(carrier, carrierCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(3, 7);
        assertThrows(CoordinateNextToAnotherException.class, () -> board.addCraft(battleship, battleshipCoordinate));
    }

    @Test
    void testAddOneCraftNextToAnotherCraft2() {
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        try {
            board.addCraft(carrier, carrierCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        Coordinate destroyerCoordinate = CoordinateFactory.createCoordinate(2, 2);
        assertThrows(CoordinateNextToAnotherException.class, () -> board.addCraft(destroyer, destroyerCoordinate));
    }

    @Test
    void testAddOneCraftCloseToAnotherCraft() {
        String expectedBoard =
                "~~~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "OOOO~~~~~~" +
                        "~~~~~~~~~~";
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(3, 8);
        try {
            board.addCraft(carrier, carrierCoordinate);
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertEquals(expectedBoard, board.toString(true));
    }

    @Test
    void testAddOneCraftCloseToAnotherCraft2() {
        String expectedBoard =
                "~~~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~®~Ω~~~~~~" +
                        "~®~Ω~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~~~~~~";
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        Coordinate destroyerCoordinate = CoordinateFactory.createCoordinate(3, 2);
        try {
            board.addCraft(carrier, carrierCoordinate);
            board.addCraft(destroyer, destroyerCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertEquals(expectedBoard, board.toString(true));
    }

    @Test
    void testAddMultipleCraft() {
        String expectedBoard =
                "~~~~Ø~~~~~" +
                        "~~~~Ø~~~Ω~" +
                        "~®~~Ø~~~Ω~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~QQQ" +
                        "~®~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~OOOO~" +
                        "~~~~~~~~~~";
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        Coordinate destroyerCoordinate = CoordinateFactory.createCoordinate(8, 1);
        Coordinate cruiserCoordinate = CoordinateFactory.createCoordinate(4, 2);
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        Coordinate submarineCoordinate = CoordinateFactory.createCoordinate(7, 5);
        try {
            board.addCraft(carrier, carrierCoordinate);
            board.addCraft(destroyer, destroyerCoordinate);
            board.addCraft(cruiser, cruiserCoordinate);
            board.addCraft(submarine, submarineCoordinate);
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertEquals(expectedBoard, board.toString(true));
    }

    @Test
    void testHitInvalid() {
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        try {
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertThrows(InvalidCoordinateException.class, () -> board.hit(CoordinateFactory.createCoordinate(8, 10)));
    }

    @Test
    void testHitWater() {
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        try {
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(ShotStatus.WATER, board.hit(CoordinateFactory.createCoordinate(4, 8)));
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testHitOk() {
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        try {
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(ShotStatus.HIT, board.hit(CoordinateFactory.createCoordinate(5, 8)));
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDestroyedWhenMust() {
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        try {
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        try {
            board.hit(CoordinateFactory.createCoordinate(5, 8));
            board.hit(CoordinateFactory.createCoordinate(6, 8));
            board.hit(CoordinateFactory.createCoordinate(7, 8));
            assertEquals(ShotStatus.DESTROYED, board.hit(CoordinateFactory.createCoordinate(8, 8)));
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testHitWithMultipleCraftUnveil() {
        String expectedBoard =
                "~~~~Ø~~~~~" +
                        "~~~~Ø~~~x~" +
                        "~®~~Ø~~~x~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~~~~" +
                        "~®~~~~~xxx" +
                        "~®~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~OOOO~" +
                        "~~~~~~~~~~";
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        Coordinate destroyerCoordinate = CoordinateFactory.createCoordinate(8, 1);
        Coordinate cruiserCoordinate = CoordinateFactory.createCoordinate(4, 2);
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        Coordinate submarineCoordinate = CoordinateFactory.createCoordinate(7, 5);
        try {
            board.addCraft(carrier, carrierCoordinate);
            board.addCraft(destroyer, destroyerCoordinate);
            board.addCraft(cruiser, cruiserCoordinate);
            board.addCraft(submarine, submarineCoordinate);
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }

        try {
            board.hit(CoordinateFactory.createCoordinate(8, 1));
            board.hit(CoordinateFactory.createCoordinate(8, 2));
            board.hit(CoordinateFactory.createCoordinate(7, 5));
            board.hit(CoordinateFactory.createCoordinate(8, 5));
            board.hit(CoordinateFactory.createCoordinate(9, 5));
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertEquals(expectedBoard, board.toString(true));
    }

    @Test
    void testHitWithMultipleCraftWithHiding() {
        String expectedBoard =
                "~~~~~~~•••" +
                        "~~~~~~~•x•" +
                        "~~~~~~~•x•" +
                        "~~~~~~~•••" +
                        "~~~~~~••••" +
                        "~~~~~~•xxx" +
                        "~~~~~~••••" +
                        "~~~~~~~~~~" +
                        "~~~~~~~~~~" +
                        "~~~~~~~~~~";
        Coordinate carrierCoordinate = CoordinateFactory.createCoordinate(1, 2);
        Coordinate destroyerCoordinate = CoordinateFactory.createCoordinate(8, 1);
        Coordinate cruiserCoordinate = CoordinateFactory.createCoordinate(4, 2);
        Coordinate battleshipCoordinate = CoordinateFactory.createCoordinate(8, 8);
        Coordinate submarineCoordinate = CoordinateFactory.createCoordinate(7, 5);
        try {
            board.addCraft(carrier, carrierCoordinate);
            board.addCraft(destroyer, destroyerCoordinate);
            board.addCraft(cruiser, cruiserCoordinate);
            board.addCraft(submarine, submarineCoordinate);
            board.addCraft(battleship, battleshipCoordinate);
        } catch (CoordinateException e) {
            e.printStackTrace();
        }

        try {
            board.hit(CoordinateFactory.createCoordinate(8, 1));
            board.hit(CoordinateFactory.createCoordinate(8, 2));
            board.hit(CoordinateFactory.createCoordinate(7, 5));
            board.hit(CoordinateFactory.createCoordinate(8, 5));
            board.hit(CoordinateFactory.createCoordinate(9, 5));
        } catch (CoordinateException e) {
            e.printStackTrace();
        }
        assertEquals(expectedBoard, board.toString(false));
    }
}