package hu.hj.craft.fleetfactory;

import hu.hj.craft.crafts.diveable.Submarine;
import hu.hj.craft.crafts.ship.Battleship;
import hu.hj.craft.crafts.ship.Carrier;
import hu.hj.craft.crafts.ship.Cruiser;
import hu.hj.craft.crafts.ship.Destroyer;
import hu.hj.craft.fleet.StandardFleet;

public class StandardFleetFactory implements FleetFactory {

    @Override
    public StandardFleet createFleet() {
        StandardFleet standardFleet = new StandardFleet();
        standardFleet.addCraft(new Destroyer());
        standardFleet.addCraft(new Submarine());
        standardFleet.addCraft(new Cruiser());
        standardFleet.addCraft(new Battleship());
        standardFleet.addCraft(new Carrier());
        return standardFleet;
    }
}
