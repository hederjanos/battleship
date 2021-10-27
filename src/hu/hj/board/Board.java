package hu.hj.board;

import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.Coordinate;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.CoordinateNextToAnotherException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;

public interface Board {

    boolean addCraft(Craft craft, Coordinate possibleAnchorCoordinate)
            throws InvalidCoordinateException, OccupiedCoordinateException, CoordinateNextToAnotherException;

    Craft getCraft(Coordinate coordinate);

    ShotStatus hit(Coordinate coordinate) throws InvalidCoordinateException, CoordinateAlreadyHitException;

    boolean areAllCraftsDestroyed();

    String toString(boolean unveil);

    int getSize();

    boolean isSeen(Coordinate coordinate);
}