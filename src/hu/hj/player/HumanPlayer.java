package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.constants.Orientation;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;
import hu.hj.director.human.HumanPlayerController;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.CoordinateNextToAnotherException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;
import hu.hj.exceptions.io.*;

import java.util.Optional;

public class HumanPlayer extends Player {

    @Override
    public boolean addCraft() throws OccupiedCoordinateException, InvalidCoordinateException, CoordinateNextToAnotherException, CraftAlreadyAddedException, InvalidCraftNameException, InvalidAddCommandFormatException, InvalidOrientationException {
        boolean craftIsAdded = false;
        Optional<AddCommand> optionalCommand = Optional.ofNullable(((HumanPlayerController) director).getNextCraftFromFleet(fleet));
        if (optionalCommand.isPresent()) {
            AddCommand command = optionalCommand.get();
            Craft craft = fleet.findCraft(command.getCraftName());
            if (craft.getStatus() != null) {
                throw new CraftAlreadyAddedException(craft.toString());
            }
            craft.setOrientation(Orientation.valueOf(command.getOrientationName()));
            craftIsAdded = board.addCraft(craft, CoordinateFactory.createCoordinate(command.getCoordinates()));
        }
        return craftIsAdded;
    }

    @Override
    public boolean shoot(Board board) throws CoordinateAlreadyHitException, InvalidCoordinateException, InvalidShotCommandFormatException {
        boolean shotIsAdded = false;
        Optional<ShotCommand> optionalCommand = Optional.ofNullable(((HumanPlayerController) director).addNextShot());
        if (optionalCommand.isPresent()) {
            ShotCommand command = optionalCommand.get();
            board.hit(CoordinateFactory.createCoordinate(command.getCoordinates()));
            shotIsAdded = true;
        }
        return shotIsAdded;
    }

    public HumanPlayerController getController() {
        return (HumanPlayerController) director;
    }

    public void setController(HumanPlayerController humanPlayerController) {
        this.director = humanPlayerController;
    }
}
