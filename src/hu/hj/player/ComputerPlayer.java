package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.constants.Orientation;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;
import hu.hj.director.strategy.Strategy;
import hu.hj.exceptions.coordinate.CoordinateAlreadyHitException;
import hu.hj.exceptions.coordinate.CoordinateNextToAnotherException;
import hu.hj.exceptions.coordinate.InvalidCoordinateException;
import hu.hj.exceptions.coordinate.OccupiedCoordinateException;

public class ComputerPlayer extends Player {

    @Override
    public boolean addCraft() throws OccupiedCoordinateException, CoordinateNextToAnotherException, InvalidCoordinateException {
        boolean craftIsAdded;
        AddCommand command = (((Strategy) director).getNextCraftFromFleet(fleet, board.getSize()));
        Craft craft = fleet.findCraft(command.getCraftName());
        craft.setOrientation(Orientation.valueOf(command.getOrientationName()));
        craftIsAdded = board.addCraft(craft, CoordinateFactory.createCoordinate(command.getCoordinates()));
        return craftIsAdded;
    }

    @Override
    public boolean shoot(Board board) throws CoordinateAlreadyHitException, InvalidCoordinateException {
        ShotCommand command = (((Strategy) director).addNextShotToTBoard(board));
        board.hit(CoordinateFactory.createCoordinate(command.getCoordinates()));
        return true;
    }

    public Strategy getStrategy() {
        return (Strategy) director;
    }

    public void setStrategy(Strategy strategy) {
        this.director = strategy;
    }
}
