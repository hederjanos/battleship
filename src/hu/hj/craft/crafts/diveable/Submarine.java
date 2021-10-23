package hu.hj.craft.crafts.diveable;

import hu.hj.constants.Symbol;
import hu.hj.craft.crafts.Craft;

public class Submarine extends Craft implements Diveable {

    private static final int[][] CLASS_SHAPE =
            {
                    {0, 0, 0, 0, 0},
                    {0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

    public Submarine() {
        this.symbol = Symbol.SUBMARINE;
        this.shape = CLASS_SHAPE;
    }
}