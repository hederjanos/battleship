package hu.hj.craft.fleet;

import hu.hj.constants.CraftStatus;
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
                break;
            }
        }
        return craftToReturn;
    }

    public boolean areAllCraftsAddedToBattlefield() {
        boolean result = true;
        for (Craft craft : crafts) {
            if (craft.getStatus() == null) {
                result = false;
                break;
            }
        }
        return result;
    }

    public List<Craft> getAllNotAddedCrafts() {
        List<Craft> unPutCrafts = new ArrayList<>();
        for (Craft craft : crafts) {
            if (craft.getStatus() == null) {
                unPutCrafts.add(craft);
            }
        }
        return unPutCrafts;
    }

    public List<Craft> getAllExistingCrafts() {
        List<Craft> existingCrafts = new ArrayList<>();
        for (Craft craft : crafts) {
            if (craft.getStatus() != CraftStatus.DESTROYED) {
                existingCrafts.add(craft);
            }
        }
        return existingCrafts;
    }

    public void printCrafts(List<Craft> crafts) {
        for (Craft craft : crafts) {
            System.out.print(craft);
            System.out.print(" ");
        }
        System.out.println();
    }
}
