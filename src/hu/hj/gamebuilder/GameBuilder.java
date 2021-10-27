package hu.hj.gamebuilder;

import hu.hj.game.Game;
import hu.hj.player.HumanPlayer;
import hu.hj.player.Player;
import hu.hj.playerbuilder.PlayerBuilder;

import java.io.BufferedReader;

public abstract class GameBuilder {

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

    public void addControllers(String type) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            playerBuilder.addController(type);
        }
    }

    public Game getGame() {
        Player playerOne = playerBuilders[0].getPlayer();
        Player playerTwo = playerBuilders[1].getPlayer();
        return new Game(playerOne, playerTwo);
    }

    public void setHumanPlayerControllerReaders(BufferedReader bufferedReader) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            Player player = playerBuilder.getPlayer();
            if (player instanceof HumanPlayer) {
                ((HumanPlayer) player).getController().setReader(bufferedReader);
            }
        }
    }

    public abstract void setPlayerBuilders();
}
