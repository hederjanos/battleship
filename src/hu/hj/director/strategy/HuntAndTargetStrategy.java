package hu.hj.director.strategy;

import hu.hj.board.Board;
import hu.hj.craft.fleet.Fleet;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;

public class HuntAndTargetStrategy implements Strategy {

    @Override
    public AddCommand getNextCraftFromFleet(Fleet fleet, int boardSize) {
        return null;
    }

    @Override
    public ShotCommand addNextShotToTBoard(Board board) {
        return null;
    }
}
