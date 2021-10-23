package hu.hj.gamebuilder;

import hu.hj.game.Game;
import hu.hj.playerbuilder.PlayerBuilder;

public abstract class GameBuilder {

    protected Game game;

    protected PlayerBuilder[] playerBuilders = new PlayerBuilder[2];

    public void addBoard(String type) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            playerBuilder.addBoard(type);
        }
    }

    public void addFleet(String type) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            playerBuilder.addFleet(type);
        }
    }

    public void addController(String type) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            playerBuilder.addController(type);
        }
    }

    public abstract void setPlayerBuilders();

    public abstract Game getGame();

}
