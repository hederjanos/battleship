package hu.hj.board;

import hu.hj.constants.Symbol;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;

public class SimpleBoardStringBuilder {

    protected final StringBuilder boardBuilder = new StringBuilder();

    protected SimpleBoardStringBuilder(Board board, boolean unveil) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Coordinate coordinate = CoordinateFactory.createCoordinate(j, i);
                Craft craft = board.getCraft(coordinate);
                if (unveil) {
                    buildIfUnveil(coordinate, craft);
                } else {
                    buildIfNotUnveil(board, coordinate, craft);
                }
            }
        }
    }

    private void buildIfUnveil(Coordinate coordinate, Craft craft) {
        if (craft != null) {
            if (craft.isHit(coordinate)) {
                boardBuilder.append(Symbol.HIT.getMark());
            } else {
                boardBuilder.append(craft.getSymbol().getMark());
            }
        } else {
            boardBuilder.append(Symbol.WATER.getMark());
        }
    }

    private void buildIfNotUnveil(Board board, Coordinate coordinate, Craft craft) {
        if (craft != null) {
            if (craft.isHit(coordinate)) {
                boardBuilder.append(Symbol.HIT.getMark());
            } else {
                boardBuilder.append(Symbol.WATER.getMark());
            }
        } else {
            if (board.isSeen(coordinate)) {
                boardBuilder.append(Symbol.SEEN.getMark());
            } else {
                boardBuilder.append(Symbol.WATER.getMark());
            }
        }
    }

    public String getStringBoard() {
        return boardBuilder.toString();
    }
}