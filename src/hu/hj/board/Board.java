package hu.hj.board;

import hu.hj.coordinate.Coordinate;
import hu.hj.craft.Craft;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.NextToAnotherException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;

public interface Board {

    boolean addCraft(Craft craft, Coordinate possibleAnchorCoordinate)
            throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherException;

    Craft getCraft(Coordinate coordinate);

    ShotStatus hit(Coordinate coordinate) throws InvalidCoordinateException, CoordinateAlreadyHitException;

    boolean areAllCraftsDestroyed();

    void show(boolean unveil);

    int getSize();

    boolean isSeen(Coordinate coordinate);
}
