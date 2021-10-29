package hu.hj.exceptions.craft;

import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.BattleshipException;

public class CraftAlreadyAddedException extends BattleshipException {

    private final Craft craft;

    public CraftAlreadyAddedException(Craft craft) {
        this.craft = craft;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName() + ": " + craft.toString();
    }
}
