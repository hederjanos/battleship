package hu.hj.craft.subs;

import hu.hj.constants.Orientation;
import hu.hj.constants.Symbol;
import hu.hj.craft.Craft;

public abstract class Sub extends Craft {

    protected Sub(Orientation orientation, Symbol symbol) {
        super(orientation, symbol);
    }
}