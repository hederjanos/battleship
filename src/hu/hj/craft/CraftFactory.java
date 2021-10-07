package hu.hj.craft;

import hu.hj.craft.ships.Ship;
import hu.hj.craft.subs.Sub;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CraftFactory {

    private CraftFactory() {
    }

    public static Craft createCraft(String craftType, String craftName, Orientation orientation) {
        Craft createdCraft = null;
        try {
            Class<?> c = getClass(craftType, craftName);
            Class<?>[] paramType = new Class[]{Orientation.class};
            if (c != null) {
                Constructor<?> constructor = c.getConstructor(paramType);
                Object[] arguments = new Object[]{orientation};
                createdCraft = (Craft) constructor.newInstance(arguments);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return createdCraft;
    }

    private static Class<?> getClass(String craftType, String craftName) throws ClassNotFoundException {
        Class<?> c = null;
        if (craftType.equals("ship")) {
            c = Class.forName(Ship.class.getPackageName() + "." + craftName);
        } else if (craftType.equals("sub")) {
            c = Class.forName(Sub.class.getPackageName() + "." + craftName);
        }
        return c;
    }
}
