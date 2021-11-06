package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.constants.Orientation;
import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;
import hu.hj.director.strategy.Strategy;
import hu.hj.exceptions.coordinate.CoordinateException;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        this.name = this.getClass().getSimpleName();
    }

    @Override
    public boolean addCraft() {
        while (true) {
            try {
                boolean craftIsAdded;
                AddCommand command = ((Strategy) director).getNextCraftFromFleet(fleet, board.getSize());
                Craft craft = fleet.findCraft(command.getCraftName());
                craft.setOrientation(Orientation.valueOf(command.getOrientationName()));
                craftIsAdded = board.addCraft(craft, CoordinateFactory.createCoordinate(command.getCoordinates()));
                return craftIsAdded;
            } catch (CoordinateException ignored) {
                //computer controlled
            }
        }
    }

    @Override
    public ShotStatus shoot(Board board) {
        while (true) {
            try {
                ShotCommand command = ((Strategy) director).addNextShotToBoard(board);
                ShotStatus shotStatus = board.hit(CoordinateFactory.createCoordinate(command.getCoordinates()));
                ((Strategy) director).refreshLastShotStatus(shotStatus);
                return shotStatus;
            } catch (CoordinateException ignored) {
                //computer controlled
            }
        }
    }

    public Strategy getStrategy() {
        return (Strategy) director;
    }

    public void setStrategy(Strategy strategy) {
        this.director = strategy;
    }
}
