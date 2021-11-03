package hu.hj.playerbuilder;

import hu.hj.board.Board;
import hu.hj.board.SimpleBoard;
import hu.hj.constants.BoardType;
import hu.hj.constants.FleetType;
import hu.hj.craft.fleetfactory.FleetFactory;
import hu.hj.craft.fleetfactory.StandardFleetFactory;
import hu.hj.player.Player;

public abstract class PlayerBuilder {

    protected Player player;

    public void addBoard(BoardType boardType) {
        Board board = null;
        if (boardType == BoardType.SIMPLE) {
            board = new SimpleBoard();
        }
        player.setBoard(board);
    }

    public void addFleet(FleetType fleetType) {
        FleetFactory fleetFactory = null;
        if (fleetType == FleetType.STANDARD) {
            fleetFactory = new StandardFleetFactory();
        }
        player.setFleet(fleetFactory);
    }

    public  Player getPlayer() {
        return player;
    }
}