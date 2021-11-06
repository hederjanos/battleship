package hu.hj.craft.fleet;

import hu.hj.constants.FleetType;

public class StandardFleet extends Fleet {

    @Override
    public FleetType getFleetType() {
        return FleetType.STANDARD;
    }
}