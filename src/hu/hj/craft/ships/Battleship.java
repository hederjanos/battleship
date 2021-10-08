package hu.hj.craft.ships;

import hu.hj.constants.Orientation;
import hu.hj.constants.Symbol;
import hu.hj.craft.CraftUtilities;

public class Battleship extends Ship {

    private static final Symbol SYMBOL = Symbol.BATTLESHIP;
    private static final int[][] SHIP_SHAPE =
            {
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Battleship(Orientation orientation) {
        super(orientation, SYMBOL);
        setShape(CraftUtilities.getOrientedShape(SHIP_SHAPE, orientation));
        setInternalAnchorCoordinate();
    }
}