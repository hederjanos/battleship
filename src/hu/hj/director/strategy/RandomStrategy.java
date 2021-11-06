package hu.hj.director.strategy;

import hu.hj.board.Board;
import hu.hj.constants.Difficulty;
import hu.hj.constants.ShotStatus;
import hu.hj.director.command.ShotCommand;

public class RandomStrategy implements Strategy {

    @Override
    public ShotCommand addNextShotToBoard(Board board) {
        int xCoordinate = random.nextInt(board.getSize());
        int yCoordinate = random.nextInt(board.getSize());
        return new ShotCommand(xCoordinate, yCoordinate);
    }

    @Override
    public void refreshLastShotStatus(ShotStatus shotStatus) {
        //computer controlled
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }
}
