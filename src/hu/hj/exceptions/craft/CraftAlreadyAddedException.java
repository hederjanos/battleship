package hu.hj.exceptions.craft;

import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.BattleshipException;

public class CraftAlreadyAddedException extends BattleshipException {

    protected final Craft craft;

    public CraftAlreadyAddedException(Craft craft) {
        this.craft = craft;
    }

    public Craft getCoordinate() {
        return craft;
    }

    @Override
    public String getMessage() {
        return craft.toString();
    }
}
