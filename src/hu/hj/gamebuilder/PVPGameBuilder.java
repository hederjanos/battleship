package hu.hj.gamebuilder;

import hu.hj.playerbuilder.HumanPlayerBuilder;

public class PVPGameBuilder extends GameBuilder {

    public void setPlayerBuilders() {
        this.playerBuilders[0] = new HumanPlayerBuilder();
        this.playerBuilders[1] = new HumanPlayerBuilder();
    }
}
