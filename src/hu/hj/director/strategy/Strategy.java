package hu.hj.director.strategy;


import hu.hj.board.Board;
import hu.hj.constants.Difficulty;
import hu.hj.constants.Orientation;
import hu.hj.constants.ShotStatus;
import hu.hj.craft.crafts.Craft;
import hu.hj.craft.fleet.Fleet;
import hu.hj.director.Director;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;

import java.util.List;
import java.util.Random;

public interface Strategy extends Director {

    Random random = new Random();

    default AddCommand getNextCraftFromFleet(Fleet fleet, int boardSize) {
        List<Craft> notAddedCrafts = fleet.getAllNotAddedCrafts();
        Craft randomCraft = notAddedCrafts.get(random.nextInt(notAddedCrafts.size()));
        String craftName = randomCraft.getClass().getSimpleName();

        Orientation randomOrientation = Orientation.values()[random.nextInt(Orientation.values().length)];
        String orientationName = randomOrientation.toString();

        int xCoordinate = random.nextInt(boardSize);
        int yCoordinate = random.nextInt(boardSize);

        return new AddCommand(craftName, orientationName, xCoordinate, yCoordinate);
    }

    ShotCommand addNextShotToBoard(Board board);

    void refreshLastShotStatus(ShotStatus shotStatus);

    Difficulty getDifficulty();
}
