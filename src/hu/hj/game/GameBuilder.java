package hu.hj.game;

import hu.hj.constants.BoardType;
import hu.hj.constants.Difficulty;
import hu.hj.constants.FleetType;
import hu.hj.constants.GameType;
import hu.hj.playerbuilder.ComputerPlayerBuilder;
import hu.hj.playerbuilder.HumanPlayerBuilder;
import hu.hj.playerbuilder.PlayerBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameBuilder {

    private final PlayerBuilder[] playerBuilders = new PlayerBuilder[2];
    private GameType gameType;

    public GameBuilder(GameType gameType, List<String> playerNames) {
        setPlayerBuilders(gameType, playerNames);
        initializeDefaultSettings(gameType);
    }

    public void setPlayerBuilders(GameType gameType, List<String> playerNames) {
        this.gameType = gameType;
        if (gameType == GameType.PVP) {
            for (int i = 0; i < playerBuilders.length; i++) {
                playerBuilders[i] = new HumanPlayerBuilder(playerNames.get(i));
            }
        } else if (gameType == GameType.PVC) {
            playerBuilders[0] = new HumanPlayerBuilder(playerNames.get(0));
            playerBuilders[1] = new ComputerPlayerBuilder();
        }
    }

    public void initializeDefaultSettings(GameType gameType) {
        addBoard(BoardType.SIMPLE);
        addFleet(FleetType.STANDARD);
        if (gameType == GameType.PVC) {
            addStrategy(Difficulty.EASY);
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

    public Game buildGame() {
        return new Game(playerBuilders[0].getPlayer(), playerBuilders[1].getPlayer());
    }

    public GameType getGameType() {
        return gameType;
    }

    public List<String> getPlayerNames() {
        return Arrays.stream(playerBuilders).map(playerBuilder -> playerBuilder.getPlayer().getName()).collect(Collectors.toList());
    }

    public PlayerBuilder getSecondPlayerBuilder() {
        return playerBuilders[1];
    }
}