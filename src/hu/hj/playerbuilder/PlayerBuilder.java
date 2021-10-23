package hu.hj.playerbuilder;

import hu.hj.board.SimpleBoard;
import hu.hj.craft.fleetfactory.StandardFleetFactory;
import hu.hj.player.Player;

public abstract class PlayerBuilder {

    protected Player player;

    public void addBoard(String type) {
        if (type.equals("SIMPLE")) {
            player.setBoard(new SimpleBoard());
        }
    }

    public void addFleet(String type) {
        if (type.equals("STANDARD")) {
            player.setFleet(new StandardFleetFactory());
        }
    }

    public abstract void addController(String type);

    public Player getPlayer() {
        return player;
    }
}
