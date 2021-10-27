package hu.hj;

import hu.hj.board.Board;
import hu.hj.board.printer.FancyBoardPrinter;
import hu.hj.board.printer.SimpleBoardPrinter;
import hu.hj.exceptions.BattleshipException;
import hu.hj.game.Game;
import hu.hj.gamebuilder.PVCGameBuilder;
import hu.hj.player.Player;
import hu.hj.ui.UserInterface;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();

        PVCGameBuilder gameBuilder = new PVCGameBuilder();

        gameBuilder.setPlayerBuilders();
        gameBuilder.addBoard("SIMPLE");
        gameBuilder.addFleet("STANDARD");
        gameBuilder.addControllers("CONSOLE", "RANDOM");
        gameBuilder.setHumanPlayerControllerReaders(ui.getReader());

        Game game = gameBuilder.getGame();

        Player player1 = game.getPlayerOne();
        Board board1 = player1.getBoard();


        while (!player1.getFleet().areAllCraftsAddedToBattlefield()) {
            try {
                SimpleBoardPrinter printer = new FancyBoardPrinter(board1.toString(true), board1.getSize());
                printer.print();
                System.out.println("AVAILABLE: ");
                player1.getFleet().printCrafts(player1.getFleet().getAllNotAddedCrafts());
                player1.addCraft();
            } catch (BattleshipException | IOException e) {
                e.printStackTrace();
            }
        }
        //Carrier NORTH 1 2;
        //Cruiser SOUTH 4 2;
        //Destroyer NORTH 8 1;
        //Battleship EAST 8 8;
        //Submarine WEST 7 5;

        /*try {
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
        printer.print();*/
    }
}
