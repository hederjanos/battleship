package hu.hj.gamebuilder;

import hu.hj.game.Game;
import hu.hj.game.PVPGame;
import hu.hj.player.Player;
import hu.hj.playerbuilder.HumanPlayerBuilder;

public class PVPGameBuilder extends GameBuilder {

    public void setPlayerBuilders() {
        this.playerBuilders[0] = new HumanPlayerBuilder();
        this.playerBuilders[1] = new HumanPlayerBuilder();
    }

    public Game getGame() {
        Player playerOne = playerBuilders[0].getPlayer();
        Player playerTwo = playerBuilders[1].getPlayer();
        return new PVPGame(playerOne, playerTwo);
    }
}
