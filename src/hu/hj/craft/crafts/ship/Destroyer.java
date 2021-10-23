package hu.hj.craft.crafts.ship;

import hu.hj.constants.Symbol;
import hu.hj.craft.crafts.Craft;

public class Destroyer extends Craft {

    private static final Symbol SYMBOL = Symbol.DESTROYER;
    private static final int[][] CLASS_SHAPE =
            {
                    {0, 0, 0, 0, 0},
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Destroyer() {
        this.symbol = Symbol.DESTROYER;
        this.shape = CLASS_SHAPE;
    }

}