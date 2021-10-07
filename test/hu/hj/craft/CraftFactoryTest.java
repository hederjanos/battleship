package hu.hj.craft;

import hu.hj.craft.ships.*;
import hu.hj.craft.subs.Submarine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CraftFactoryTest {

    @Test
    void testCreateCraftOk() {
        Craft craft;
        for (Orientation orientation : Orientation.values()) {
            craft = CraftFactory.createCraft("ship", "Battleship", orientation);
            assertTrue(craft instanceof Battleship);
            craft = CraftFactory.createCraft("ship", "Carrier", orientation);
            assertTrue(craft instanceof Carrier);
            craft = CraftFactory.createCraft("ship", "Cruiser", orientation);
            assertTrue(craft instanceof Cruiser);
            craft = CraftFactory.createCraft("ship", "Destroyer", orientation);
            assertTrue(craft instanceof Destroyer);
            craft = CraftFactory.createCraft("sub", "Submarine", orientation);
            assertTrue(craft instanceof Submarine);
        }
    }
}