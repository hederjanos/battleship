package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.director.Director;
import hu.hj.craft.fleet.Fleet;
import hu.hj.craft.fleetfactory.FleetFactory;
import hu.hj.exceptions.BattleshipException;

public abstract class Player {

    protected Board board;
    protected Fleet fleet;
    protected Director director;

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

    public Director getDirector() {
        return director;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public abstract boolean addCraft() throws BattleshipException;

    public abstract boolean shoot(Board board) throws BattleshipException;

}
