package hu.hj.craft.ships;

import hu.hj.craft.CraftUtilities;
import hu.hj.craft.Orientation;

public class Battleship extends Ship {

    public static final char SYMBOL = 'O';
    private static final int[][] SHIP_SHAPE =
            {
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Battleship(Orientation orientation) {
        super(orientation);
        setShape(CraftUtilities.getOrientedShape(SHIP_SHAPE, orientation));
        setInternalAnchorCoordinate();
    }
}
