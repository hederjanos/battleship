package hu.hj.craft.fleet;

import hu.hj.craft.crafts.Craft;

import java.util.ArrayList;
import java.util.List;

public abstract class Fleet {

    protected List<Craft> crafts;

    protected Fleet() {
        this.crafts = new ArrayList<>();
    }

    public void addCraft(Craft craft) {
        crafts.add(craft);
    }

    public Craft findCraft(String craftType) {
        Craft craftToReturn = null;
        for (Craft craft : crafts) {
            if (craft.getClass().getSimpleName().equals(craftType)) {
                craftToReturn = craft;
            }
        }
        return craftToReturn;
    }
}
