package hu.hj.printer;

import hu.hj.board.Board;

public class SimpleBoardPrinter extends AbstractPrinter {

    public SimpleBoardPrinter(Board board, boolean unveil) {
        super(board, unveil);
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(boardBuilder.charAt(i * size + j));
            }
            if (i < size - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
