package hu.hj.player;

import hu.hj.constants.Orientation;
import hu.hj.controller.command.AddCommand;
import hu.hj.controller.human.HumanPlayerController;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.CoordinateNextToAnotherException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;
import hu.hj.exceptions.craft.CraftAlreadyAddedException;
import hu.hj.exceptions.io.BattleshipIOException;

public class HumanPlayer extends Player {

    private HumanPlayerController humanPlayerController;

    public void addCraft() throws OccupiedCoordinateException, InvalidCoordinateException, CoordinateNextToAnotherException, CraftAlreadyAddedException, BattleshipIOException {
        AddCommand command = humanPlayerController.getNextCraft();
        Craft craft = fleet.findCraft(command.getCraftName());
        if (craft.getStatus() != null) {
            throw new CraftAlreadyAddedException(craft);
        }
        craft.setOrientation(Orientation.valueOf(command.getOrientationName()));
        board.addCraft(craft, CoordinateFactory.createCoordinate(command.getCoordinates()));
    }

    public HumanPlayerController getController() {
        return humanPlayerController;
    }

    public void setHumanPlayerController(HumanPlayerController humanPlayerController) {
        this.humanPlayerController = humanPlayerController;
    }

}
