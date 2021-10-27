package hu.hj.game;

import hu.hj.player.Player;

public class Game {

    protected Player playerOne;
    protected Player playerTwo;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\n" +
                "One: " +
                playerOne.getClass().getSimpleName() + " " +
                playerOne.getBoard().getClass().getSimpleName() + " " +
                playerOne.getFleet().getClass().getSimpleName() + " " +
                playerOne.getController().getClass().getSimpleName() + " " + "\n" +
                "Two: " +
                playerTwo.getClass().getSimpleName() + " " +
                playerTwo.getBoard().getClass().getSimpleName() + " " +
                playerTwo.getFleet().getClass().getSimpleName() + " " +
                playerTwo.getController().getClass().getSimpleName() + " " + "\n";
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
