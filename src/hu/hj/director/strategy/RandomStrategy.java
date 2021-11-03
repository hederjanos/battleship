package hu.hj.director.strategy;

import hu.hj.board.Board;
import hu.hj.constants.Orientation;
import hu.hj.craft.crafts.Craft;
import hu.hj.craft.fleet.Fleet;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {

    private final Random random = new Random();

    @Override
    public AddCommand getNextCraftFromFleet(Fleet fleet, int boardSize) {
        List<Craft> notAddedCrafts = fleet.getAllNotAddedCrafts();
        Craft randomCraft = notAddedCrafts.get(random.nextInt(notAddedCrafts.size()));
        String craftName = randomCraft.getClass().getSimpleName();

        Orientation randomOrientation = Orientation.values()[random.nextInt(Orientation.values().length)];
        String orientationName = randomOrientation.toString();

        int xCoordinate = random.nextInt(boardSize);
        int yCoordinate = random.nextInt(boardSize);
        int[] coordinates = new int[]{xCoordinate, yCoordinate};

        return new AddCommand(craftName, orientationName, coordinates);
    }

    @Override
    public ShotCommand addNextShotToTBoard(Board board) {
        int xCoordinate = random.nextInt(board.getSize());
        int yCoordinate = random.nextInt(board.getSize());
        int[] coordinates = new int[]{xCoordinate, yCoordinate};

        return new ShotCommand(coordinates);
    }
}
