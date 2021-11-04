package hu.hj.game;

import hu.hj.constants.Colour;
import hu.hj.game.boardprinter.FancyBoardPrinter;
import hu.hj.game.boardprinter.SimpleBoardPrinter;

import java.io.PrintStream;

public class Printer {

    PrintStream printStream;
    SimpleBoardPrinter boardPrinter;

    public Printer() {
        this.printStream = new PrintStream(System.out);
        this.boardPrinter = new FancyBoardPrinter(printStream);
    }

    public void printGreeting() {
        printStream.println("Welcome!");
        printStream.println("\n" + "-------------------------------------");
        printStream.println("             BATTLESHIP              ");
        printStream.println("-------------------------------------" + "\n");
        printStream.println("This is a two players battleship game.");
        printStream.println("Default game type is human versus computer.");
    }

    public void printAddYourName() {
        printStream.println("\nEnter your name!");
    }

    public void printAddYourOpponentName() {
        printStream.println("\nEnter your opponent name if you want to play in PVP mode");
    }

    public void printGoodbye() {
        printStream.println("\nGood bye!");
    }

    public void printBoard(String stringBoard, int boardSize) {
        boardPrinter.print(stringBoard, boardSize);
    }


    public void printMenuItem(String menuItem) {
        printStream.print(menuItem);
    }

    public void printExceptionMessage(Exception e) {
        printStream.println(Colour.ANSI_RED.getColourCode() + e.getMessage());
        printStream.println(Colour.ANSI_RESET.getColourCode());
    }

    public void printSetInfo(String parameter, String value) {
        printStream.println(parameter + " set to " + value + ".");
    }

    public void printSettling() {
        printStream.println("\n" + "-------------------------------------");
        printStream.println("              Settling               ");
        printStream.println("-------------------------------------" + "\n");
    }

    public void printSettlingInfo(String playerName) {
        printStream.println("\n" + playerName + ", please settle down your fleet.");
        printStream.println("Your board is: ");
    }

    public void printFleet(String stringFleet) {
        printStream.print("\nAvailable Crafts: ");
        printStream.print(stringFleet + "\n");
    }

    public void printAddCraftInstruction(String playerName) {
        printStream.println("\nExamples for valid adding formats: carrier(car) north(n) b 3. Case insensitive.");
        printStream.println("Press Q(q) to quit to main menu.");
        printStream.println(playerName + ", type your command: ");
    }

    public void printAddingCraftsIsDone(String playerName) {
        printStream.println("\nThe fleet of " + playerName + " has been successfully settled down!");
    }

    public void printBattle() {
        printStream.println("\n" + "-------------------------------------");
        printStream.println("               Battle                ");
        printStream.println("-------------------------------------" + "\n");
    }

    public void printShootingInfo(String currentPlayer) {
        printStream.println("\n" + currentPlayer + ", please give a shot.");
        printStream.println("The board of the opponent is: ");
    }

    public void printShootInstruction(String playerName) {
        printStream.println("\nExamples for valid shot formats: b 3. Case insensitive.");
        printStream.println("Press Q(q) to quit to main menu.");
        printStream.println(playerName + ", type your command: ");
    }

    public void printGameOver(String playerName) {
        printStream.println("\nCongratulations to " + playerName + ", she/he won!");
    }

    public void printGameOver() {
        printStream.println("\n" + "-------------------------------------");
        printStream.println("              Game Over              ");
        printStream.println("-------------------------------------" + "\n");
    }
}