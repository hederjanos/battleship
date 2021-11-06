package hu.hj.game;

import hu.hj.constants.Colour;
import hu.hj.game.boardprinter.FancyBoardPrinter;
import hu.hj.game.boardprinter.SimpleBoardPrinter;

import java.io.PrintStream;

public class Printer {

    private static final String SEPARATOR = "-------------------------------------";
    private final PrintStream printStream;
    private final SimpleBoardPrinter boardPrinter;

    public Printer() {
        this.printStream = new PrintStream(System.out);
        this.boardPrinter = new FancyBoardPrinter(printStream);
    }

    public void printGreeting() {
        printStream.println("Welcome!");
        printStream.println("\n" + SEPARATOR);
        printStream.println("             BATTLESHIP              ");
        printStream.println(SEPARATOR + "\n");
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

    public void printBoard(String playerName, String stringBoard, int boardSize) {
        printStream.println("The board of " + playerName + ":");
        boardPrinter.print(stringBoard, boardSize);
    }

    public void printMenuItem(String menuItem) {
        printStream.print(menuItem);
    }

    public void printGameNotExist() {
        printStream.println("Game does not exist. Please, select new game option!");
    }

    public void printExceptionMessage(Exception e) {
        printStream.println(Colour.ANSI_RED.getColourCode() + e.getMessage());
        printStream.println(Colour.ANSI_RESET.getColourCode());
    }

    public void printSetInfo(String parameter, String value) {
        printStream.println(parameter + " set to " + value + ".");
    }

    public void printSettling() {
        printStream.println("\n" + SEPARATOR);
        printStream.println("              Settling               ");
        printStream.println(SEPARATOR + "\n");
    }

    public void printSettlingInfo(String playerName) {
        printStream.println("\n" + playerName + ", please settle down your fleet.");
    }

    public void printAvailableCrafts(String stringFleet) {
        printStream.print("\nAvailable crafts: ");
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
        printStream.println("\n" + SEPARATOR);
        printStream.println("               Battle                ");
        printStream.println(SEPARATOR + "\n");
    }

    public void printShootingInfo(String currentPlayer) {
        printStream.println("\n" + currentPlayer + ", please give a shot.");
    }

    public void printExistingCrafts(String stringFleet) {
        printStream.print("\nExisting crafts from the fleet of the opponent: ");
        printStream.print(stringFleet + "\n");
    }

    public void printShootInstruction(String playerName) {
        printStream.println("\nExamples for valid shot formats: b 3. Case insensitive.");
        printStream.println("Press Q(q) to quit to main menu.");
        printStream.println(playerName + ", type your command: ");
    }

    public void printShotStatus(String playerName, String shotStatus) {
        if (shotStatus.equals("WATER")) {
            printStream.println(Colour.ANSI_RED.getColourCode() + playerName + " hit water.");
            printStream.println(Colour.ANSI_RESET.getColourCode());
        } else if (shotStatus.equals("HIT")) {
            printStream.println(Colour.ANSI_GREEN.getColourCode() + playerName + " hit a craft.");
            printStream.println(Colour.ANSI_RESET.getColourCode());
        } else {
            printStream.println(Colour.ANSI_GREEN.getColourCode() + playerName + " destroyed a craft.");
            printStream.println(Colour.ANSI_RESET.getColourCode());
        }
    }

    public void printGameOver(String playerName) {
        printStream.println("\n" + SEPARATOR);
        printStream.println("              Game Over              ");
        printStream.println(SEPARATOR + "\n");
        printStream.println("\nCongratulations to " + playerName + ", you won!");
    }

    public void close() {
        printStream.close();
    }
}