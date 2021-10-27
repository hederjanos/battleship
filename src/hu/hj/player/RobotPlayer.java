package hu.hj.player;

import hu.hj.controller.strategy.Strategy;
import hu.hj.exceptions.BattleshipException;

public class RobotPlayer extends Player {

    Strategy strategy;

    @Override
    public void addCraft() throws BattleshipException {
    }

    @Override
    public Strategy getController() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
