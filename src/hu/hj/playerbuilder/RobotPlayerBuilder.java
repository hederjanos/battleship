package hu.hj.playerbuilder;

import hu.hj.controller.strategy.HuntAndTargetStrategy;
import hu.hj.controller.strategy.RandomStrategy;
import hu.hj.player.RobotPlayer;

public class RobotPlayerBuilder extends PlayerBuilder {

    public RobotPlayerBuilder() {
        player = new RobotPlayer();
    }

    public void addController(String type) {
        if (type.equals("RANDOM")) {
            ((RobotPlayer) player).setStrategy(new RandomStrategy());
        } else if (type.equals("HUNTANDTARGET")) {
            ((RobotPlayer) player).setStrategy(new HuntAndTargetStrategy());
        }
    }
}
