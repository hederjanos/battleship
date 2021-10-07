package hu.hj;

import hu.hj.board.Board;
import hu.hj.board.SimpleBoard;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.Craft;
import hu.hj.craft.CraftFactory;
import hu.hj.craft.Orientation;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.NextToAnotherException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;

public class Main {

    public static void main(String[] args) {
        Board board = new SimpleBoard();
        try {
            Craft craft = CraftFactory.createCraft("ship", "Carrier", Orientation.NORTH);
            Coordinate coordinate = CoordinateFactory.createCoordinate(1, 2);
            board.addCraft(craft, coordinate);
            craft = CraftFactory.createCraft("ship", "Cruiser", Orientation.SOUTH);
            coordinate = CoordinateFactory.createCoordinate(4, 2);
            board.addCraft(craft, coordinate);
            craft = CraftFactory.createCraft("ship", "Destroyer", Orientation.NORTH);
            coordinate = CoordinateFactory.createCoordinate(8, 1);
            board.addCraft(craft, coordinate);
            craft = CraftFactory.createCraft("ship", "Battleship", Orientation.EAST);
            coordinate = CoordinateFactory.createCoordinate(8, 8);
            board.addCraft(craft, coordinate);
            craft = CraftFactory.createCraft("sub", "Submarine", Orientation.WEST);
            coordinate = CoordinateFactory.createCoordinate(7, 5);
            board.addCraft(craft, coordinate);
        } catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherException e) {
            e.printStackTrace();
        }
        try {
            board.hit(CoordinateFactory.createCoordinate(8, 1));
            board.hit(CoordinateFactory.createCoordinate(8, 2));
            board.hit(CoordinateFactory.createCoordinate(7, 5));
            board.hit(CoordinateFactory.createCoordinate(8, 5));
            board.hit(CoordinateFactory.createCoordinate(9, 5));

        } catch (InvalidCoordinateException | CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }
        board.show(true);
        board.show(false);
    }
}
