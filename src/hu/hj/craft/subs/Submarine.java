package hu.hj.craft.subs;

import hu.hj.craft.CraftUtilities;
import hu.hj.craft.Orientation;

public class Submarine extends Sub {

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