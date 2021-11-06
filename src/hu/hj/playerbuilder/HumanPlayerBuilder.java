package hu.hj.playerbuilder;

import hu.hj.director.human.HumanPlayerControllerConsole;
import hu.hj.player.HumanPlayer;

import java.util.Scanner;

public class HumanPlayerBuilder extends PlayerBuilder {

    public HumanPlayerBuilder(String name) {
        this.player = new HumanPlayer(name);
    }

    public void addController(Scanner scanner) {
        ((HumanPlayer) player).setController(new HumanPlayerControllerConsole(scanner));
    }
}
