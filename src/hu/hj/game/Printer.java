package hu.hj.game;

import hu.hj.constants.Colour;
import hu.hj.game.boardprinter.FancyBoardPrinter;
import hu.hj.game.boardprinter.SimpleBoardPrinter;

import java.io.PrintStream;

public class Printer {

    PrintStream printStream = new PrintStream(System.out);
    SimpleBoardPrinter boardPrinter;

    public Printer() {
        this.boardPrinter = new FancyBoardPrinter(printStream);
    }

    public void printGreeting() {
        System.out.println("Welcome!");
        System.out.println("\n" + "-------------------------------------");
        System.out.println("             BATTLESHIP              ");
        System.out.println("-------------------------------------" + "\n");
    }

    public void printGoodbye() {
        System.out.println("\nGood bye!");
    }

    public void printBoard(String stringBoard, int boardSize) {
        boardPrinter.print(stringBoard, boardSize);
    }

    public void printFleet(String stringFleet) {
        printStream.print("\nAvailable Crafts: ");
        printStream.print(stringFleet + "\n");
    }

    public void printMenuItem(String menuItem) {
        printStream.print(menuItem);
    }

    public void printExceptionMessage(Exception e) {
        printStream.println(Colour.ANSI_RED.getColourCode() + e.getMessage());
        printStream.println(Colour.ANSI_RESET.getColourCode());
    }

    public void printAddCraftInstruction(String playerName) {
        printStream.println("\nExamples for valid adding formats: carrier\\car north\\n b 3. Case insensitive.");
        printStream.println("Press Q\\q to quit to main menu.");
        printStream.println(playerName + ", type your command: ");
    }

    public void printSetInfo(String parameter, String value) {
        printStream.println(parameter + " set to " + value + ".");
    }

    public void printAddingCraftsIsDone(String playerName) {
        printStream.println("\nThe fleet of " + playerName + " has been successfully settled down!");
    }

    public void printSettlingInfo(String playerName) {
        printStream.println("\n" + playerName + ", please settle down your fleet.");
        printStream.println("Your board is: ");
    }

    public void printShootingInfo(String currentPlayer) {
        printStream.println("\n" + currentPlayer + ", please give a shot.");
        printStream.println("The board of the opponent is: ");
    }

    public void printShootInstruction(String playerName) {
        printStream.println("\nExamples for valid shot formats: b 3. Case insensitive.");
        printStream.println("Press Q\\q to quit to main menu.");
        printStream.println(playerName + ", type your command: ");
    }

    public void printGameOver(String playerName) {
        printStream.println("\nCongratulations to " + playerName + ", she/he won!");
    }

    public void printSettling() {
        System.out.println("\n" + "-------------------------------------");
        System.out.println("              Settling               ");
        System.out.println("-------------------------------------" + "\n");
    }

    public void printBattle() {
        System.out.println("\n" + "-------------------------------------");
        System.out.println("               Battle                ");
        System.out.println("-------------------------------------" + "\n");
    }

    public void printGameOver() {
        System.out.println("\n" + "-------------------------------------");
        System.out.println("              Game Over              ");
        System.out.println("-------------------------------------" + "\n");
    }
}