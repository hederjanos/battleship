package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.controller.Controller;
import hu.hj.craft.fleet.Fleet;
import hu.hj.craft.fleetfactory.FleetFactory;
import hu.hj.exceptions.BattleshipException;

import java.io.IOException;

public abstract class Player {

    protected Board board;
    protected Fleet fleet;

    protected Player() {
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setFleet(FleetFactory fleetFactory) {
        this.fleet = fleetFactory.createFleet();
    }

    public Board getBoard() {
        return board;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public abstract void addCraft() throws BattleshipException, IOException;

    public abstract Controller getController();
}
