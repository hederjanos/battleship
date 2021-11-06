package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.constants.CraftStatus;
import hu.hj.constants.Orientation;
import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;
import hu.hj.director.human.HumanPlayerController;
import hu.hj.exceptions.coordinate.CoordinateException;
import hu.hj.exceptions.io.*;

import java.util.Optional;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    public boolean addCraft() throws CoordinateException, CraftAlreadyAddedException, InvalidCraftNameException, InvalidAddCommandFormatException, InvalidOrientationException {
        boolean craftIsAdded = false;
        Optional<AddCommand> optionalCommand = Optional.ofNullable(((HumanPlayerController) director).getNextCraftFromFleet(fleet));
        if (optionalCommand.isPresent()) {
            AddCommand command = optionalCommand.get();
            Craft craft = fleet.findCraft(command.getCraftName());
            if (craft.getStatus() != CraftStatus.NOT_ADDED) {
                throw new CraftAlreadyAddedException(craft.toString());
            }
            craft.setOrientation(Orientation.valueOf(command.getOrientationName()));
            craftIsAdded = board.addCraft(craft, CoordinateFactory.createCoordinate(command.getCoordinates()));
        }
        return craftIsAdded;
    }

    @Override
    public ShotStatus shoot(Board board) throws CoordinateException, InvalidShotCommandFormatException {
        ShotStatus shotStatus = null;
        Optional<ShotCommand> optionalCommand = Optional.ofNullable(((HumanPlayerController) director).addNextShot());
        if (optionalCommand.isPresent()) {
            ShotCommand command = optionalCommand.get();
            shotStatus = board.hit(CoordinateFactory.createCoordinate(command.getCoordinates()));
        }
        return shotStatus;
    }

    public HumanPlayerController getController() {
        return (HumanPlayerController) director;
    }

    public void setController(HumanPlayerController humanPlayerController) {
        this.director = humanPlayerController;
    }
}
