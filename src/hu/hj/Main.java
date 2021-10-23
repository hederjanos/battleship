package hu.hj;

import hu.hj.board.Board;
import hu.hj.board.printer.FancyBoardPrinter;
import hu.hj.board.printer.SimpleBoardPrinter;
import hu.hj.constants.Orientation;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.NextToAnotherException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;
import hu.hj.game.Game;
import hu.hj.gamebuilder.GameBuilder;
import hu.hj.gamebuilder.PVCGameBuilder;
import hu.hj.player.Player;

public class Main {

    public static void main(String[] args) {
        GameBuilder gameBuilder = new PVCGameBuilder();

        gameBuilder.setPlayerBuilders();
        gameBuilder.addBoard("SIMPLE");
        gameBuilder.addFleet("STANDARD");
        gameBuilder.addController("RANDOM");

        Game game = gameBuilder.getGame();

        Player player1 = game.getPlayerOne();
        Board board1 = player1.getBoard();

        Player player2 = game.getPlayerTwo();
        Board board2 = player2.getBoard();

        try {

            Craft craft = player1.getFleet().findCraft("Carrier");
            craft.setOrientation(Orientation.NORTH);
            Coordinate coordinate = CoordinateFactory.createCoordinate(1, 2);
            board1.addCraft(craft, coordinate);


            craft = player1.getFleet().findCraft("Cruiser");
            craft.setOrientation(Orientation.SOUTH);
            coordinate = CoordinateFactory.createCoordinate(4, 2);
            board1.addCraft(craft, coordinate);


            craft = player1.getFleet().findCraft("Destroyer");
            craft.setOrientation(Orientation.NORTH);
            coordinate = CoordinateFactory.createCoordinate(8, 1);
            board1.addCraft(craft, coordinate);


            craft = player1.getFleet().findCraft("Battleship");
            craft.setOrientation(Orientation.EAST);
            coordinate = CoordinateFactory.createCoordinate(8, 8);
            board1.addCraft(craft, coordinate);


            craft = player1.getFleet().findCraft("Submarine");
            craft.setOrientation(Orientation.WEST);
            coordinate = CoordinateFactory.createCoordinate(7, 5);
            board1.addCraft(craft, coordinate);


        } catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherException e) {
            e.printStackTrace();
        }
        try {
            board1.hit(CoordinateFactory.createCoordinate(8, 1));
            board1.hit(CoordinateFactory.createCoordinate(8, 2));
            board1.hit(CoordinateFactory.createCoordinate(7, 5));
            board1.hit(CoordinateFactory.createCoordinate(8, 5));
            board1.hit(CoordinateFactory.createCoordinate(9, 5));

        } catch (InvalidCoordinateException | CoordinateAlreadyHitException e) {
            e.printStackTrace();
        }

        SimpleBoardPrinter printer = new FancyBoardPrinter(board1.toString(true), board1.getSize());
        printer.print();

        printer = new FancyBoardPrinter(board1.toString(false), board1.getSize());
        printer.print();

        printer = new FancyBoardPrinter(board2.toString(false), board2.getSize());
        printer.print();

        printer = new FancyBoardPrinter(board2.toString(true), board2.getSize());
        printer.print();
    }
}
