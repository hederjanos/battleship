package hu.hj.craft.fleet;

import hu.hj.craft.ships.Battleship;
import hu.hj.craft.ships.Carrier;
import hu.hj.craft.ships.Cruiser;
import hu.hj.craft.ships.Destroyer;
import hu.hj.craft.subs.Submarine;

import java.util.HashMap;
import java.util.Map;

public class StandardFleet {

    private static final Map<Class<?>, Integer> FLEET = new HashMap<>();

    static {
        try {
            FLEET.putIfAbsent(Class.forName(Battleship.class.getCanonicalName()), 1);
            FLEET.putIfAbsent(Class.forName(Carrier.class.getCanonicalName()), 1);
            FLEET.putIfAbsent(Class.forName(Cruiser.class.getCanonicalName()), 1);
            FLEET.putIfAbsent(Class.forName(Destroyer.class.getCanonicalName()), 1);
            FLEET.putIfAbsent(Class.forName(Submarine.class.getCanonicalName()), 1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private StandardFleet() {
    }

    public static Map<Class<?>, Integer> getFleet() {
        return FLEET;
    }
}