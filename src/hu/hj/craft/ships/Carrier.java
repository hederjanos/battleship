package hu.hj.craft.ships;

import hu.hj.craft.CraftUtilities;
import hu.hj.craft.Orientation;

public class Carrier extends Ship {

    public static final char SYMBOL = '®';
    private static final int[][] SHIP_SHAPE =
            {
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0}
            };

    public Carrier(Orientation orientation) {
        super(orientation, SYMBOL);
        setShape(CraftUtilities.getOrientedShape(SHIP_SHAPE, orientation));
        setInternalAnchorCoordinate();
    }
}
