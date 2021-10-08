package hu.hj.craft.ships;

import hu.hj.constants.Orientation;
import hu.hj.constants.Symbol;
import hu.hj.craft.CraftUtilities;

public class Carrier extends Ship {

    private static final Symbol SYMBOL = Symbol.CARRIER;
    private static final int[][] CLASS_SHAPE =
            {
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0}
            };

    public Carrier(Orientation orientation) {
        super(orientation, SYMBOL);
        setShape(CraftUtilities.getOrientedShape(CLASS_SHAPE, orientation));
        setInternalAnchorCoordinate();
    }
}