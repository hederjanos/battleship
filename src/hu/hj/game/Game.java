package hu.hj.game;

import hu.hj.board.Board;
import hu.hj.constants.GameStatus;
import hu.hj.constants.ShotStatus;
import hu.hj.craft.fleet.Fleet;
import hu.hj.exceptions.BattleshipException;
import hu.hj.player.HumanPlayer;
import hu.hj.player.Player;

public class Game {

    private final Player[] players;
    private GameStatus currentGameStatus;
    private boolean isPaused = false;
    private int currentPlayerIndex;
    private Printer printer;

    public Game(Player... players) {
        this.players = players;
        this.currentGameStatus = GameStatus.SETTLING;
    }

    public void run() {
        if (isPaused) {
            isPaused = false;
        }
        if (currentGameStatus == GameStatus.SETTLING) {
            initializeBoards();
        }
        if (currentGameStatus == GameStatus.BATTLE) {
            battle();
        }
        if (currentGameStatus == GameStatus.GAME_OVER) {
            close();
        }
    }

    private void initializeBoards() {
        printer.printSettling();
        for (int i = 0; i < players.length; i++) {
            if (!isPaused) {
                currentPlayerIndex = i;
                initializeBoard(players[i] instanceof HumanPlayer);
            }
        }
        refreshGameStatus();
        manageTurn();
    }

    private void initializeBoard(boolean printerInAction) {
        Player currentPlayer = getCurrentPlayer();
        Board board = currentPlayer.getBoard();
        Fleet fleet = currentPlayer.getFleet();
        if (printerInAction) {
            printer.printSettlingInfo(currentPlayer.getName());
            printer.printBoard(currentPlayer.getName(), board.toString(true), board.getSize());
        }
        while (!fleet.areAllCraftsAddedToBattlefield() && !isPaused) {
            try {
                if (printerInAction) {
                    printer.printAvailableCrafts(fleet.toString(fleet.getAllNotAddedCrafts()));
                    printer.printAddCraftInstruction(currentPlayer.getName());
                }
                if (!currentPlayer.addCraft()) {
                    isPaused = true;
                    continue;
                }
                if (printerInAction) {
                    printer.printBoard(currentPlayer.getName(), board.toString(true), board.getSize());
                }
            } catch (BattleshipException e) {
                printer.printExceptionMessage(e);
            }
        }
        if (fleet.areAllCraftsAddedToBattlefield()) {
            printer.printAddingCraftsIsDone(currentPlayer.getName());
        }
    }

    private void battle() {
        printer.printBattle();
        while (currentGameStatus != GameStatus.GAME_OVER && !isPaused) {
            try {
                makeCurrentPlayerToShoot();
                if (!isPaused) {
                    manageTurn();
                    refreshGameStatus();
                }
            } catch (BattleshipException e) {
                printer.printExceptionMessage(e);
            }
        }
    }

    private void manageTurn() {
        currentPlayerIndex = currentPlayerIndex == 0 ? 1 : 0;
    }

    private void refreshGameStatus() {
        if (getCurrentPlayer().getFleet().areAllCraftsAddedToBattlefield()
            && getOppositePlayer().getFleet().areAllCraftsAddedToBattlefield()) {
            currentGameStatus = GameStatus.BATTLE;
        }
        if (!getCurrentPlayer().getFleet().isInFleetExistingCraft()
            || !getOppositePlayer().getFleet().isInFleetExistingCraft()) {
            currentGameStatus = GameStatus.GAME_OVER;
        }
    }

    private void makeCurrentPlayerToShoot() throws BattleshipException {
        Player currentPlayer = getCurrentPlayer();
        Board oppositeBoard = getOppositePlayer().getBoard();
        Fleet oppositeFleet = getOppositePlayer().getFleet();
        if (currentPlayer instanceof HumanPlayer) {
            printer.printShootingInfo(currentPlayer.getName());
            printer.printBoard(getOppositePlayer().getName(), oppositeBoard.toString(false), oppositeBoard.getSize());
            printer.printExistingCrafts(oppositeFleet.getAllExistingCrafts().toString());
            printer.printShootInstruction(currentPlayer.getName());
        }
        ShotStatus shotStatus = currentPlayer.shoot(oppositeBoard);
        if (shotStatus == null) {
            isPaused = true;
        } else {
            printer.printBoard(getOppositePlayer().getName(), oppositeBoard.toString(false), oppositeBoard.getSize());
            printer.printShotStatus(currentPlayer.getName(), shotStatus.toString());
        }
    }

    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    private Player getOppositePlayer() {
        return currentPlayerIndex == 0 ? players[1] : players[0];
    }

    private void close() {
        printer.printGameOver(getOppositePlayer().getName());
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public GameStatus getCurrentGameStatus() {
        return currentGameStatus;
    }
}