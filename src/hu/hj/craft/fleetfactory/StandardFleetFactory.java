package hu.hj.craft.fleetfactory;

import hu.hj.craft.crafts.diveable.Submarine;
import hu.hj.craft.crafts.ship.Battleship;
import hu.hj.craft.crafts.ship.Carrier;
import hu.hj.craft.crafts.ship.Cruiser;
import hu.hj.craft.crafts.ship.Destroyer;
import hu.hj.craft.fleet.Fleet;
import hu.hj.craft.fleet.StandardFleet;

public class StandardFleetFactory extends FleetFactory {

    @Override
    public Fleet createFleet() {
        Fleet fleet = new StandardFleet();
        fleet.addCraft(new Destroyer());
        fleet.addCraft(new Submarine());
        fleet.addCraft(new Cruiser());
        fleet.addCraft(new Battleship());
        fleet.addCraft(new Carrier());
        return fleet;
    }
}
