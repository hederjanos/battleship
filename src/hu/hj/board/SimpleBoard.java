package hu.hj.board;

import hu.hj.coordinate.Coordinate;
import hu.hj.craft.Craft;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.NextToAnotherException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;
import hu.hj.printer.AbstractPrinter;
import hu.hj.printer.FancyBoardPrinter;
import hu.hj.printer.SimpleBoardPrinter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleBoard implements Board {

    public static final int SIZE = 10;

    private final Map<Coordinate, Craft> battlefield = new HashMap<>();
    protected final Set<Coordinate> seenCoordinates = new HashSet<>();
    private int numberOfCrafts;
    private int destroyedCrafts;

    public boolean addCraft(Craft craft, Coordinate possibleAnchorCoordinate)
            throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherException {
        Set<Coordinate> craftPossibleCoordinates = craft.getAbsoluteCoordinates(possibleAnchorCoordinate);

        checkIfCraftFitInBattlefield(possibleAnchorCoordinate, craftPossibleCoordinates);

        checkCraftNeighbourhood(craft, possibleAnchorCoordinate);

        craft.setAnchorCoordinate(possibleAnchorCoordinate);

        addCraftToBattlefield(craft, craftPossibleCoordinates);

        return true;
    }

    private void checkIfCraftFitInBattlefield(Coordinate possibleAnchorCoordinate, Set<Coordinate> craftPossibleCoordinates)
            throws InvalidCoordinateException, OccupiedCoordinateException {
        for (Coordinate coordinate : craftPossibleCoordinates) {
            if (!checkCoordinate(coordinate)) {
                throw new InvalidCoordinateException(possibleAnchorCoordinate);
            } else if (getCraft(coordinate) != null) {
                throw new OccupiedCoordinateException(coordinate);
            }
        }
    }

    private boolean checkCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return (x >= 0 && x < SIZE && y >= 0 && y < SIZE);
    }

    private void checkCraftNeighbourhood(Craft craft, Coordinate possibleAnchorCoordinate) throws NextToAnotherException {
        Set<Coordinate> craftNeighbourCoordinates = getNeighbourhood(craft, possibleAnchorCoordinate);
        for (Coordinate neighbourCoordinate : craftNeighbourCoordinates) {
            if (getCraft(neighbourCoordinate) != null) {
                throw new NextToAnotherException(neighbourCoordinate);
            }
        }
    }

    private void addCraftToBattlefield(Craft craft, Set<Coordinate> craftPossibleCoordinates) {
        this.numberOfCrafts++;
        for (Coordinate coordinate : craftPossibleCoordinates) {
            this.battlefield.put(coordinate, craft);
        }
    }

    public Craft getCraft(Coordinate coordinate) {
        if (battlefield.containsKey(coordinate)) {
            return this.battlefield.get(coordinate);
        }
        return null;
    }

    public Set<Coordinate> getNeighbourhood(Craft craft) {
        return getNeighbourhood(craft, craft.getAnchorCoordinate());
    }

    private Set<Coordinate> getNeighbourhood(Craft craft, Coordinate anchorCoordinate) {
        if (craft == null || anchorCoordinate == null) {
            throw new IllegalArgumentException();
        }
        Set<Coordinate> craftAbsoluteCoordinates = craft.getAbsoluteCoordinates(anchorCoordinate);
        Set<Coordinate> craftNeighbourCoordinates = new HashSet<>();

        for (Coordinate coordinate : craftAbsoluteCoordinates) {
            Set<Coordinate> neighbourCoordinates = coordinate.getAdjacentCoordinates();
            for (Coordinate neighbourCoordinate : neighbourCoordinates) {
                if (checkCoordinate(neighbourCoordinate) && !craftAbsoluteCoordinates.contains(neighbourCoordinate)) {
                    craftNeighbourCoordinates.add(neighbourCoordinate);
                }
            }
        }
        return craftNeighbourCoordinates;
    }

    public ShotStatus hit(Coordinate coordinate) throws InvalidCoordinateException, CoordinateAlreadyHitException {
        ShotStatus shotStatus = ShotStatus.HIT;
        if (!checkCoordinate(coordinate)) {
            throw new InvalidCoordinateException(coordinate);
        }
        Craft craft = getCraft(coordinate);
        if (craft == null) {
            seenCoordinates.add(coordinate.copy());
            shotStatus = ShotStatus.WATER;
        } else {
            if (craft.hit(coordinate)) {
                seenCoordinates.add(coordinate.copy());
                if (craft.isShotDown()) {
                    revealCraftNeighbourhood(craft);
                    shotStatus = ShotStatus.DESTROYED;
                }
            }
        }
        return shotStatus;
    }

    private void revealCraftNeighbourhood(Craft craft) {
        Set<Coordinate> neighbourCoordinates = getNeighbourhood(craft);
        for (Coordinate neighbourCoordinate : neighbourCoordinates) {
            if (checkCoordinate(neighbourCoordinate)) {
                seenCoordinates.add(neighbourCoordinate);
            }
        }
        this.destroyedCrafts++;
    }

    public boolean isSeen(Coordinate coordinate) {
        return seenCoordinates.contains(coordinate);
    }

    public int getSize() {
        return SIZE;
    }

    public boolean areAllCraftsDestroyed() {
        return numberOfCrafts == destroyedCrafts;
    }

    public void show(boolean unveil) {
        AbstractPrinter printer = new SimpleBoardPrinter(this, unveil);
        printer.print();
        printer = new FancyBoardPrinter(this, unveil);
        printer.print();
    }
}