package hu.hj.director.strategy;


import hu.hj.board.Board;
import hu.hj.craft.fleet.Fleet;
import hu.hj.director.Director;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;

public interface Strategy extends Director {

    AddCommand getNextCraftFromFleet(Fleet fleet, int boardSize);

    ShotCommand addNextShotToTBoard(Board board);
}
