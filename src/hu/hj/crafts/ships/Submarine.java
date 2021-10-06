package hu.hj.crafts.ships;

import hu.hj.crafts.CraftUtilities;
import hu.hj.crafts.Orientation;

public class Submarine extends Ship {

    public static final char SYMBOL = 'Q';
    private static final int[][] SHIP_SHAPE =
            {
                    {0, 0, 0, 0, 0},
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Submarine(Orientation orientation) {
        super(orientation);
        setShape(CraftUtilities.getOrientedShape(SHIP_SHAPE, orientation));
        setInternalAnchorCoordinate();
    }
}
