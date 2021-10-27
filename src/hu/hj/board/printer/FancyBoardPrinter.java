package hu.hj.board.printer;

import hu.hj.constants.Colour;
import hu.hj.constants.Symbol;

public class FancyBoardPrinter implements SimpleBoardPrinter {

    private static final int TAB = 4;
    private static final String H_FRAME = "+-----";
    private static final String PLUS = "+";
    private static final String V_FRAME = "|  ";
    private static final String OR = "|";

    private final String stringBoard;
    private final int size;

    public FancyBoardPrinter(String stringBoard, int size) {
        this.stringBoard = stringBoard;
        this.size = size;
    }

    @Override
    public void print() {
        printABC();
        for (int i = 0; i < size; i++) {
            printHorizontalFrame();
            printLine(i);
        }
        printHorizontalFrame();
    }

    private void printABC() {
        System.out.print(createSpaces(TAB));
        for (char j = 'A'; j < size + 'A'; j++) {
            System.out.print(createSpaces(3) + j + createSpaces(2));
        }
        System.out.print(createSpaces(1));
        System.out.println();
    }

    private void printHorizontalFrame() {
        System.out.print(createSpaces(TAB));
        for (int j = 0; j < size; j++) {
            System.out.print(Colour.ANSI_BLUE.getColourCode() + H_FRAME);
        }
        System.out.print(PLUS);
        System.out.println(Colour.ANSI_RESET.getColourCode());
    }

    private void printLine(int i) {
        System.out.printf(String.format("%s%%2d  ", Colour.ANSI_RESET.getColourCode()), i + 1);
        for (int j = 0; j < size; j++) {
            System.out.print(Colour.ANSI_BLUE.getColourCode() + V_FRAME);
            char currentCharacter = stringBoard.charAt(i * size + j);
            if (currentCharacter == Symbol.WATER.getMark()) {
                System.out.print(createSpaces(1));
            } else if (currentCharacter == Symbol.HIT.getMark()) {
                System.out.print(Colour.ANSI_RED.getColourCode() + currentCharacter);
            } else if (currentCharacter == Symbol.SEEN.getMark()) {
                System.out.print(Colour.ANSI_PURPLE.getColourCode() + currentCharacter);
            } else {
                System.out.print(Colour.ANSI_GREEN.getColourCode() + currentCharacter);
            }
            System.out.print(createSpaces(2));
        }
        System.out.print(Colour.ANSI_BLUE.getColourCode() + OR);
        System.out.println();
    }

    private String createSpaces(int number) {
        return " " + " ".repeat(number - 1);
    }
}