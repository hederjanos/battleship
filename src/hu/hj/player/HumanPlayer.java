package hu.hj.player;

import hu.hj.constants.Orientation;
import hu.hj.controller.human.HumanPlayerController;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.exceptions.coordinate.CoordinateNextToAnotherException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;
import hu.hj.exceptions.craft.CraftAlreadyAddedException;

import java.io.IOException;

public class HumanPlayer extends Player {

    private HumanPlayerController humanPlayerController;

    public void addCraft() throws OccupiedCoordinateException, InvalidCoordinateException, CoordinateNextToAnotherException, IOException, CraftAlreadyAddedException {
        String command = humanPlayerController.getNextCraft();
        String[] commandParts = command.split(" ");

        Craft craft = fleet.findCraft(commandParts[0]);
        if (craft.getStatus() != null) {
            throw new CraftAlreadyAddedException(craft);
        }
        craft.setOrientation(Orientation.valueOf(commandParts[1]));
        Coordinate coordinate = CoordinateFactory.createCoordinate(Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[3]));
        board.addCraft(craft, coordinate);
    }

    public HumanPlayerController getController() {
        return humanPlayerController;
    }

    public void setHumanPlayerController(HumanPlayerController humanPlayerController) {
        this.humanPlayerController = humanPlayerController;
    }

}
