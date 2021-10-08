package hu.hj.craft;

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

    protected final Orientation orientation;
    protected final Symbol symbol;
    protected int[][] shape;
    protected Coordinate internalAnchorCoordinate;
    protected Coordinate anchorCoordinate;

    protected Craft(Orientation orientation, Symbol symbol) {
        this.orientation = orientation;
        this.symbol = symbol;
    }

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

    protected boolean isHit(Coordinate coordinate) {
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

    protected Orientation getOrientation() {
        return orientation;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    protected int[][] getShape() {
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
}