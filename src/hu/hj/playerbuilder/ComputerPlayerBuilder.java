package hu.hj.playerbuilder;

import hu.hj.constants.Difficulty;
import hu.hj.director.strategy.HuntAndTargetStrategy;
import hu.hj.director.strategy.RandomStrategy;
import hu.hj.director.strategy.Strategy;
import hu.hj.player.ComputerPlayer;

public class ComputerPlayerBuilder extends PlayerBuilder {

    public ComputerPlayerBuilder() {
        player = new ComputerPlayer();
    }

    public void addStrategy(Difficulty difficulty) {
        Strategy strategy = null;
        if (difficulty == Difficulty.EASY) {
            strategy = new RandomStrategy();
        } else if (difficulty == Difficulty.HARD) {
            strategy = new HuntAndTargetStrategy();
        }
        ((ComputerPlayer) player).setStrategy(strategy);
    }
}
