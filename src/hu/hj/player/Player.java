package hu.hj.player;

import hu.hj.board.Board;
import hu.hj.constants.ShotStatus;
import hu.hj.craft.fleet.Fleet;
import hu.hj.craft.fleetfactory.FleetFactory;
import hu.hj.director.Director;
import hu.hj.exceptions.BattleshipException;

public abstract class Player {

    protected String name;
    protected Board board;
    protected Fleet fleet;
    protected Director director;

    protected Player() {
    }

    protected Player(String name) {
        this.name = name;
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
        return name;
    }

    public abstract boolean addCraft() throws BattleshipException;

    public abstract ShotStatus shoot(Board board) throws BattleshipException;

}
