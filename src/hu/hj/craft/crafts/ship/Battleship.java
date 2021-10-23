package hu.hj.craft.crafts.ship;

import hu.hj.constants.Symbol;
import hu.hj.craft.crafts.Craft;

public class Battleship extends Craft {

    private static final int[][] CLASS_SHAPE =
            {
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Battleship() {
        this.symbol = Symbol.BATTLESHIP;
        this.shape = CLASS_SHAPE;
    }
}