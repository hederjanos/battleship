package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.controller.Controller;
import hu.hj.craft.fleet.Fleet;
import hu.hj.craft.fleetfactory.FleetFactory;

public abstract class Player {

    protected Board board;
    protected Fleet fleet;
    protected Controller controller;

    protected Player() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
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

    public Controller getController() {
        return controller;
    }
}
