package hu.hj.board;

import hu.hj.constants.BoardType;
import hu.hj.constants.CraftStatus;
import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.Coordinate;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleBoard implements Board {

    private static final int MAX_BOARD_SIZE = 20;
    private static final int MIN_BOARD_SIZE = 5;

    private final Map<Coordinate, Craft> battlefield;
    protected final Set<Coordinate> seenCoordinates;
    private final int size;

    public SimpleBoard() {
        this(10);
    }

    public SimpleBoard(int size) {
        if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid board size: " + size + ". The size of board must be between 5 and 20!");
        } else {
            this.size = size;
        }
        this.battlefield = new HashMap<>();
        this.seenCoordinates = new HashSet<>();
    }

    public boolean addCraft(Craft craft, Coordinate possibleAnchorCoordinate)
            throws InvalidCoordinateException, OccupiedCoordinateException, CoordinateNextToAnotherException {
        Set<Coordinate> craftPossibleCoordinates = craft.getAbsoluteCoordinates(possibleAnchorCoordinate);

        checkIfCraftFitInBattlefield(possibleAnchorCoordinate, craftPossibleCoordinates);

        checkCraftNeighbourhood(craft, possibleAnchorCoordinate);

        craft.setAnchorCoordinate(possibleAnchorCoordinate);
        craft.setStatus(CraftStatus.INTACT);

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
        return (x >= 0 && x < size && y >= 0 && y < size);
    }

    private void checkCraftNeighbourhood(Craft craft, Coordinate possibleAnchorCoordinate) throws CoordinateNextToAnotherException {
        Set<Coordinate> craftNeighbourCoordinates = getNeighbourhood(craft, possibleAnchorCoordinate);
        for (Coordinate neighbourCoordinate : craftNeighbourCoordinates) {
            if (getCraft(neighbourCoordinate) != null) {
                throw new CoordinateNextToAnotherException(neighbourCoordinate);
            }
        }
    }

    private void addCraftToBattlefield(Craft craft, Set<Coordinate> craftPossibleCoordinates) {
        for (Coordinate coordinate : craftPossibleCoordinates) {
            battlefield.put(coordinate, craft);
        }
    }

    public Craft getCraft(Coordinate coordinate) {
        if (battlefield.containsKey(coordinate)) {
            return battlefield.get(coordinate);
        }
        return null;
    }

    private Set<Coordinate> getNeighbourhood(Craft craft) {
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

    public ShotStatus hit(Coordinate coordinate) throws InvalidCoordinateException, CoordinateAlreadyHitException, CoordinateAlreadySeenException {
        ShotStatus shotStatus = ShotStatus.HIT;
        if (!checkCoordinate(coordinate)) {
            throw new InvalidCoordinateException(coordinate);
        }
        if (seenCoordinates.contains(coordinate)) {
            throw new CoordinateAlreadySeenException(coordinate);
        }
        Craft craft = getCraft(coordinate);
        if (craft == null) {
            seenCoordinates.add(coordinate.copy());
            shotStatus = ShotStatus.WATER;
        } else {
            if (craft.hit(coordinate)) {
                craft.setStatus(CraftStatus.HIT);
                if (craft.isShotDown()) {
                    revealCraftNeighbourhood(craft);
                    craft.setStatus(CraftStatus.DESTROYED);
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
    }

    public boolean isSeen(Coordinate coordinate) {
        return seenCoordinates.contains(coordinate);
    }

    @Override
    public BoardType getBoardType() {
        return BoardType.SIMPLE;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString(boolean unveil) {
        return new SimpleBoardStringBuilder(this, unveil).getStringBoard();
    }
}