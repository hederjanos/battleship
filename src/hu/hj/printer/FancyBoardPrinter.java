package hu.hj.printer;

import hu.hj.board.Board;

public class FancyBoardPrinter extends AbstractPrinter {

    private static final int TAB = 4;
    private static final String H_FRAME = "+-----";
    private static final String PLUS = "+";
    private static final String V_FRAME = "|  ";
    private static final String OR = "|";

    public FancyBoardPrinter(Board board, boolean unveil) {
        super(board, unveil);
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
            System.out.print(H_FRAME);
        }
        System.out.print(PLUS);
        System.out.println();
    }

    private void printLine(int i) {
        System.out.printf(Colour.ANSI_RESET + "%2d  ", i + 1);
        for (int j = 0; j < size; j++) {
            System.out.print(Colour.ANSI_RESET + V_FRAME);
            char currentCharacter = boardBuilder.charAt(i * size + j);
            switch (currentCharacter) {
                case WATER_SYMBOL:
                    System.out.print(Colour.ANSI_BLUE + createSpaces(1));
                    break;
                case HIT_SYMBOL:
                    System.out.print(Colour.ANSI_RED + HIT_SYMBOL);
                    break;
                case SEEN_SYMBOL:
                    System.out.print(Colour.ANSI_PURPLE + SEEN_SYMBOL);
                    break;
                default:
                    System.out.print(Colour.ANSI_GREEN + currentCharacter);
            }
            System.out.print(createSpaces(2));
        }
        System.out.print(Colour.ANSI_RESET + OR);
        System.out.println();
    }


    private String createSpaces(int number) {
        return " " + " ".repeat(number - 1);
    }
}