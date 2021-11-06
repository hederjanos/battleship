package hu.hj.game.boardprinter;

import java.io.PrintStream;

public class RawBoardPrinter implements SimpleBoardPrinter {

    private final PrintStream printStream;

    public RawBoardPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void print(String stringBoard, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                printStream.print(stringBoard.charAt(i * size + j));
            }
            if (i < size - 1) {
                printStream.println();
            }
        }
        printStream.println();
    }
}