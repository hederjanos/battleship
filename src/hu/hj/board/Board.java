package hu.hj.board;

import hu.hj.constants.BoardType;
import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.Coordinate;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.CoordinateException;

public interface Board {

    boolean addCraft(Craft craft, Coordinate possibleAnchorCoordinate) throws CoordinateException;

    Craft getCraft(Coordinate coordinate);

    ShotStatus hit(Coordinate coordinate) throws CoordinateException;

    String toString(boolean unveil);

    int getSize();

    boolean isSeen(Coordinate coordinate);

    BoardType getBoardType();
}