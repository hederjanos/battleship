package hu.hj.game.boardprinter;

public class RawBoardPrinter implements SimpleBoardPrinter {

    private final String stringBoard;
    private final int size;

    public RawBoardPrinter(String stringBoard, int size) {
        this.stringBoard = stringBoard;
        this.size = size;
    }

    public void print(String stringBoard, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(stringBoard.charAt(i * size + j));
            }
            if (i < size - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
}