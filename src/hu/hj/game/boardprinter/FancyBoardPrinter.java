package hu.hj.game.boardprinter;

import hu.hj.constants.Colour;
import hu.hj.constants.Symbol;

import java.io.PrintStream;

public class FancyBoardPrinter implements SimpleBoardPrinter {

    private static final int TAB = 4;
    private static final String H_FRAME = "+-----";
    private static final String PLUS = "+";
    private static final String V_FRAME = "|  ";
    private static final String OR = "|";

    private final PrintStream printStream;

    public FancyBoardPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(String stringBoard, int boardSize) {
        printABC(boardSize);
        for (int i = 0; i < boardSize; i++) {
            printHorizontalFrame(boardSize);
            printLine(stringBoard, boardSize, i);
        }
        printHorizontalFrame(boardSize);
    }

    private void printABC(int boardSize) {
        printStream.print(createSpaces(TAB));
        for (char j = 'A'; j < boardSize + 'A'; j++) {
            printStream.print(createSpaces(3) + j + createSpaces(2));
        }
        printStream.print(createSpaces(1));
        printStream.println();
    }

    private void printHorizontalFrame(int boardSize) {
        printStream.print(createSpaces(TAB));
        for (int j = 0; j < boardSize; j++) {
            printStream.print(Colour.ANSI_BLUE.getColourCode() + H_FRAME);
        }
        printStream.print(PLUS);
        printStream.println(Colour.ANSI_RESET.getColourCode());
    }

    private void printLine(String stringBoard, int boardSize, int i) {
        printStream.printf(String.format("%s%%2d  ", Colour.ANSI_RESET.getColourCode()), i + 1);
        for (int j = 0; j < boardSize; j++) {
            printStream.print(Colour.ANSI_BLUE.getColourCode() + V_FRAME);
            char currentCharacter = stringBoard.charAt(i * boardSize + j);
            if (currentCharacter == Symbol.WATER.getMark()) {
                printStream.print(createSpaces(1));
            } else if (currentCharacter == Symbol.HIT.getMark()) {
                printStream.print(Colour.ANSI_RED.getColourCode() + currentCharacter);
            } else if (currentCharacter == Symbol.SEEN.getMark()) {
                printStream.print(Colour.ANSI_PURPLE.getColourCode() + currentCharacter);
            } else {
                printStream.print(Colour.ANSI_GREEN.getColourCode() + currentCharacter);
            }
            printStream.print(createSpaces(2));
        }
        printStream.print(Colour.ANSI_BLUE.getColourCode() + OR);
        printStream.println();
    }

    private String createSpaces(int number) {
        return " " + " ".repeat(number - 1);
    }
}