package hu.hj.gamebuilder;

import hu.hj.game.Game;
import hu.hj.game.PVCGame;
import hu.hj.player.Player;
import hu.hj.playerbuilder.HumanPlayerBuilder;
import hu.hj.playerbuilder.RobotPlayerBuilder;

public class PVCGameBuilder extends GameBuilder {

    public void setPlayerBuilders() {
        this.playerBuilders[0] = new HumanPlayerBuilder();
        this.playerBuilders[1] = new RobotPlayerBuilder();
    }

    @Override
    public void addController(String type) {
        playerBuilders[0].addController("CONSOLE");
        playerBuilders[1].addController(type);
    }

    public Game getGame() {
        Player playerOne = playerBuilders[0].getPlayer();
        Player playerTwo = playerBuilders[1].getPlayer();
        return new PVCGame(playerOne, playerTwo);
    }
}
