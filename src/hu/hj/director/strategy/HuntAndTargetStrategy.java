package hu.hj.director.strategy;

import hu.hj.board.Board;
import hu.hj.constants.Difficulty;
import hu.hj.constants.Orientation;
import hu.hj.constants.ShotStatus;
import hu.hj.coordinate.Coordinate;
import hu.hj.coordinate.CoordinateFactory;
import hu.hj.craft.crafts.Craft;
import hu.hj.craft.fleet.Fleet;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HuntAndTargetStrategy implements Strategy {

    private ShotStatus lastShotStatus;
    private Coordinate lastShotCoordinate;
    private final Queue<Coordinate> lastShotCoordinateAdjacency = new LinkedList<>();

    @Override
    public AddCommand getNextCraftFromFleet(Fleet fleet, int boardSize) {
        List<Craft> notAddedCrafts = fleet.getAllNotAddedCrafts();
        Craft randomCraft = notAddedCrafts.get(random.nextInt(notAddedCrafts.size()));
        String craftName = randomCraft.getClass().getSimpleName();

        Orientation randomOrientation = Orientation.values()[random.nextInt(Orientation.values().length)];
        String orientationName = randomOrientation.toString();

        int[] coordinates = generateCoordinates(boardSize);

        return new AddCommand(craftName, orientationName, coordinates);
    }

    private int[] generateCoordinates(int boardSize) {
        int xCoordinate = random.nextInt(boardSize);
        int yCoordinate;
        if (xCoordinate == 0 || xCoordinate == boardSize - 1) {
            yCoordinate = random.nextInt(boardSize);
        } else {
            yCoordinate = (random.nextInt(2)) == 0 ? 0 : boardSize - 1;
        }
        return new int[]{xCoordinate, yCoordinate};
    }

    @Override
    public ShotCommand addNextShotToBoard(Board board) {
        int xCoordinate;
        int yCoordinate;
        if (lastShotStatus == null || lastShotStatus == ShotStatus.WATER || lastShotStatus == ShotStatus.DESTROYED) {
            if (lastShotCoordinateAdjacency.isEmpty()) {
                xCoordinate = random.nextInt(board.getSize());
                yCoordinate = random.nextInt(board.getSize());
                lastShotCoordinate = CoordinateFactory.createCoordinate(xCoordinate, yCoordinate);
            } else {
                lastShotCoordinate = lastShotCoordinateAdjacency.remove();
            }
        } else {
            lastShotCoordinateAdjacency.addAll(lastShotCoordinate.getOrthogonalAdjacentCoordinates());
            lastShotCoordinate = lastShotCoordinateAdjacency.remove();
        }
        return new ShotCommand(lastShotCoordinate.getX(), lastShotCoordinate.getY());
    }

    @Override
    public void refreshLastShotStatus(ShotStatus shotStatus) {
        this.lastShotStatus = shotStatus;
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.HARD;
    }
}