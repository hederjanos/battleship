package hu.hj.craft.subs;

import hu.hj.constants.Orientation;
import hu.hj.constants.Symbol;
import hu.hj.craft.CraftUtilities;

public class Submarine extends Sub {

    private static final Symbol SYMBOL = Symbol.SUBMARINE;
    private static final int[][] SHIP_SHAPE =
            {
                    {0, 0, 0, 0, 0},
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Submarine(Orientation orientation) {
        super(orientation, SYMBOL);
        setShape(CraftUtilities.getOrientedShape(SHIP_SHAPE, orientation));
        setInternalAnchorCoordinate();
    }
}