package hu.hj.director.human;


import hu.hj.craft.fleet.Fleet;
import hu.hj.director.Director;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;
import hu.hj.exceptions.io.InvalidAddCommandFormatException;
import hu.hj.exceptions.io.InvalidCraftNameException;
import hu.hj.exceptions.io.InvalidOrientationException;
import hu.hj.exceptions.io.InvalidShotCommandFormatException;

public interface HumanPlayerController extends Director {

    AddCommand getNextCraftFromFleet(Fleet fleet) throws InvalidAddCommandFormatException, InvalidCraftNameException, InvalidOrientationException;

    ShotCommand addNextShot() throws InvalidShotCommandFormatException;

}