package hu.hj.printer;

import hu.hj.board.Board;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.Craft;

public abstract class AbstractPrinter {

    protected static final char HIT_SYMBOL = 'x';
    protected static final char WATER_SYMBOL = '~';
    protected static final char SEEN_SYMBOL = '•';

    protected final StringBuilder boardBuilder = new StringBuilder();
    protected final int size;

    protected AbstractPrinter(Board board, boolean unveil) {
        this.size = board.getSize();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Coordinate coordinate = CoordinateFactory.createCoordinate(j, i);
                Craft craft = board.getCraft(coordinate);
                if (unveil) {
                    buildIfUnveil(board, coordinate, craft);
                } else {
                    buildIfNotUnveil(board, coordinate, craft);
                }
            }
        }
    }

    private void buildIfUnveil(Board board, Coordinate coordinate, Craft craft) {
        if (craft != null) {
            if (!board.isSeen(coordinate)) {
                boardBuilder.append(craft.getSymbol());
            } else {
                boardBuilder.append(HIT_SYMBOL);
            }
        } else {
            boardBuilder.append(WATER_SYMBOL);
        }
    }

    private void buildIfNotUnveil(Board board, Coordinate coordinate, Craft craft) {
        if (craft != null) {
            if (!board.isSeen(coordinate)) {
                boardBuilder.append(WATER_SYMBOL);
            } else {
                boardBuilder.append(HIT_SYMBOL);
            }
        } else {
            if (!board.isSeen(coordinate)) {
                boardBuilder.append(WATER_SYMBOL);
            } else {
                boardBuilder.append(SEEN_SYMBOL);
            }
        }
    }

    public abstract void print();
}