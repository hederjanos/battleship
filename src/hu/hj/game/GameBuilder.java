package hu.hj.game;

import hu.hj.constants.BoardType;
import hu.hj.constants.Difficulty;
import hu.hj.constants.FleetType;
import hu.hj.constants.GameType;
import hu.hj.playerbuilder.ComputerPlayerBuilder;
import hu.hj.playerbuilder.HumanPlayerBuilder;
import hu.hj.playerbuilder.PlayerBuilder;

import java.util.Scanner;

public class GameBuilder {

    private final PlayerBuilder[] playerBuilders = new PlayerBuilder[2];
    private GameType gameType;

    public GameBuilder(GameType gameType) {
        setPlayerBuilders(gameType);
        initializeDefaultSettings(gameType);
    }

    public void initializeDefaultSettings(GameType gameType) {
        addBoard(BoardType.SIMPLE);
        addFleet(FleetType.STANDARD);
        if (gameType == GameType.PVC) {
            addStrategy(Difficulty.EASY);
        }
    }

    public void setPlayerBuilders(GameType gameType) {
        this.gameType = gameType;
        if (gameType == GameType.PVP) {
            for (int i = 0; i < playerBuilders.length; i++) {
                playerBuilders[i] = new HumanPlayerBuilder();
            }
        } else if (gameType == GameType.PVC) {
            playerBuilders[0] = new HumanPlayerBuilder();
            playerBuilders[1] = new ComputerPlayerBuilder();
        }
    }

    public void addBoard(BoardType boardType) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            playerBuilder.addBoard(boardType);
        }
    }

    public void addFleet(FleetType fleetType) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            playerBuilder.addFleet(fleetType);
        }
    }

    public void addController(Scanner scanner) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            if (playerBuilder instanceof HumanPlayerBuilder) {
                ((HumanPlayerBuilder) playerBuilder).addController(scanner);
            }
        }
    }

    public void addStrategy(Difficulty difficulty) {
        for (PlayerBuilder playerBuilder : playerBuilders) {
            if (playerBuilder instanceof ComputerPlayerBuilder) {
                ((ComputerPlayerBuilder) playerBuilder).addStrategy(difficulty);
            }
        }
    }

    public Game getGame() {
        return new Game(playerBuilders[0].getPlayer(), playerBuilders[1].getPlayer());
    }

    public GameType getGameType() {
        return gameType;
    }
}
