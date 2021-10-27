package hu.hj.gamebuilder;

import hu.hj.playerbuilder.HumanPlayerBuilder;
import hu.hj.playerbuilder.RobotPlayerBuilder;

public class PVCGameBuilder extends GameBuilder {

    public void setPlayerBuilders() {
        this.playerBuilders[0] = new HumanPlayerBuilder();
        this.playerBuilders[1] = new RobotPlayerBuilder();
    }

    public void addControllers(String typeOne, String typeTwo) {
        playerBuilders[0].addController(typeOne);
        playerBuilders[1].addController(typeTwo);
    }
}
