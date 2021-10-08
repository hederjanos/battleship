package hu.hj.craft.ships;

import hu.hj.constants.Orientation;
import hu.hj.constants.Symbol;
import hu.hj.craft.Craft;

public abstract class Ship extends Craft {

    protected Ship(Orientation orientation, Symbol symbol) {
        super(orientation, symbol);
    }
}