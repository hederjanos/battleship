package hu.hj.craft.crafts;

import hu.hj.constants.CraftStatus;
import hu.hj.constants.Orientation;
import hu.hj.constants.Symbol;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;

import java.util.HashSet;
import java.util.Set;

public abstract class Craft {

    private static final int HIT_VALUE = -1;
    private static final int CRAFT_VALUE = 1;
    private static final int ANCHOR_VALUE = 2;

    protected Orientation orientation;
    protected Symbol symbol;
    protected CraftStatus status;
    protected int[][] shape;
    protected Coordinate internalAnchorCoordinate;
    protected Coordinate anchorCoordinate;

    public Set<Coordinate> getAbsoluteCoordinates() {
        return getAbsoluteCoordinates(anchorCoordinate);
    }

    public Set<Coordinate> getAbsoluteCoordinates(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException();
        }
        Set<Coordinate> absoluteCoordinates = new HashSet<>();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape.length; j++) {
                if (shape[i][j] == ANCHOR_VALUE) {
                    absoluteCoordinates.add(coordinate);
                } else if (shape[i][j] == CRAFT_VALUE || shape[i][j] == HIT_VALUE) {
                    int x = j - internalAnchorCoordinate.getX();
                    int y = i - internalAnchorCoordinate.getY();
                    Coordinate relativeCoordinateToInternalAnchor = CoordinateFactory.createCoordinate(x, y);
                    absoluteCoordinates.add(relativeCoordinateToInternalAnchor.add(coordinate));
                }
            }
        }
        return absoluteCoordinates;
    }

    public boolean hit(Coordinate coordinate) throws CoordinateAlreadyHitException {
        Set<Coordinate> absoluteCoordinates = getAbsoluteCoordinates();
        if (absoluteCoordinates.contains(coordinate)) {
            Coordinate internalCoordinate = coordinate.subtract(anchorCoordinate.subtract(internalAnchorCoordinate));
            int i = internalCoordinate.getY();
            int j = internalCoordinate.getX();
            if (shape[i][j] == HIT_VALUE) {
                throw new CoordinateAlreadyHitException(coordinate);
            } else {
                shape[i][j] = HIT_VALUE;
                return true;
            }
        }
        return false;
    }

    public boolean isHit(Coordinate coordinate) {
        boolean isHit = false;
        Set<Coordinate> absoluteCoordinates = getAbsoluteCoordinates();
        if (absoluteCoordinates.contains(coordinate)) {
            Coordinate internalCoordinate = coordinate.subtract(anchorCoordinate.subtract(internalAnchorCoordinate));
            int i = internalCoordinate.getY();
            int j = internalCoordinate.getX();
            if (shape[i][j] == HIT_VALUE) {
                isHit = true;
            }
        }
        return isHit;
    }

    public boolean isShotDown() {
        boolean isShotDown = true;
        Set<Coordinate> absoluteCoordinates = getAbsoluteCoordinates();
        for (Coordinate coordinate : absoluteCoordinates) {
            Coordinate internalCoordinate = coordinate.subtract(anchorCoordinate.subtract(internalAnchorCoordinate));
            int i = internalCoordinate.getY();
            int j = internalCoordinate.getX();
            if (shape[i][j] == CRAFT_VALUE || shape[i][j] == ANCHOR_VALUE) {
                isShotDown = false;
                break;
            }
        }
        return isShotDown;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public CraftStatus getStatus() {
        return status;
    }

    public int[][] getShape() {
        return shape;
    }

    public Coordinate getInternalAnchorCoordinate() {
        return internalAnchorCoordinate;
    }

    public Coordinate getAnchorCoordinate() {
        Coordinate coordinate = null;
        if (anchorCoordinate != null) {
            coordinate = anchorCoordinate.copy();
        }
        return coordinate;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        setShape(CraftUtilities.getOrientedShape(shape, orientation));
        setInternalAnchorCoordinate();
    }

    public void setStatus(CraftStatus status) {
        this.status = status;
    }

    protected void setShape(int[][] shape) {
        this.shape = shape;
    }

    protected void setInternalAnchorCoordinate() {
        int[] coordinatesOfInternalAnchor = CraftUtilities.searchPositionInShape(shape, ANCHOR_VALUE);
        this.internalAnchorCoordinate = CoordinateFactory.createCoordinate(coordinatesOfInternalAnchor);
    }

    public void setAnchorCoordinate(Coordinate coordinate) {
        this.anchorCoordinate = coordinate.copy();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(": ");
        for (int[] ints : shape) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sb.append(symbol.getMark());
                }
            }
        }
        return sb.toString();
    }
}