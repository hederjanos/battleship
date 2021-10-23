package hu.hj.playerbuilder;

import hu.hj.controller.human.HumanPlayerControllerConsole;
import hu.hj.player.HumanPlayer;

public class HumanPlayerBuilder extends PlayerBuilder {

    public HumanPlayerBuilder() {
       player = new HumanPlayer();
    }

    public void addController(String type) {
        if (type.equals("CONSOLE")) {
            player.setController(new HumanPlayerControllerConsole());
        }
    }
}
