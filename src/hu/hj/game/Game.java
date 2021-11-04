package hu.hj.game;

import hu.hj.board.Board;
import hu.hj.constants.GameStatus;
import hu.hj.craft.fleet.Fleet;
import hu.hj.exceptions.BattleshipException;
import hu.hj.player.Player;

public class Game {

    private final Player[] players;
    private GameStatus currentGameStatus;
    private int currentPlayerIndex;
    private Printer printer;

    public Game(Player... players) {
        this.players = players;
        currentGameStatus = GameStatus.NOT_STARTED;
    }

    public void run() {
        if (currentGameStatus == GameStatus.NOT_STARTED) {
            initializeBoards();
        }
        if (currentGameStatus == GameStatus.SETTLING) {
            battle();
        }
        if (currentGameStatus == GameStatus.GAME_OVER) {
            close();
        }
    }

    private void initializeBoards() {
        this.currentGameStatus = GameStatus.SETTLING;
        printer.printSettling();
        for (int i = 0; i < players.length; i++) {
            currentPlayerIndex = i;
            initializeBoard();
        }
        manageTurn();
    }

    private void initializeBoard() {
        Player currentPlayer = getCurrentPlayer();
        Board board = currentPlayer.getBoard();
        Fleet fleet = currentPlayer.getFleet();
        printer.printSettlingInfo(currentPlayer.getName());
        printer.printBoard(board.toString(true), board.getSize());
        while (!fleet.areAllCraftsAddedToBattlefield() && currentGameStatus == GameStatus.SETTLING) {
            try {
                printer.printFleet(fleet.toString(fleet.getAllNotAddedCrafts()));
                printer.printAddCraftInstruction(currentPlayer.getName());
                if (!currentPlayer.addCraft()) {
                    currentGameStatus = GameStatus.QUIT;
                    continue;
                }
                printer.printBoard(board.toString(true), board.getSize());
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
        this.currentGameStatus = GameStatus.BATTLE;
        do {
            try {
                makeCurrentPlayerToShoot();
            } catch (BattleshipException e) {
                printer.printExceptionMessage(e);
                continue;
            }
            manageTurn();
            refreshGameStatus();
        } while (currentGameStatus == GameStatus.BATTLE);
    }

    private void manageTurn() {
        currentPlayerIndex = currentPlayerIndex == 0 ? 1 : 0;
    }

    private void refreshGameStatus() {
        if (getCurrentPlayer().getBoard().areAllCraftsDestroyed()
            || getOppositePlayer().getBoard().areAllCraftsDestroyed()) {
            currentGameStatus = GameStatus.GAME_OVER;
        }
    }

    private void makeCurrentPlayerToShoot() throws BattleshipException {
        Player currentPlayer = getCurrentPlayer();
        Board oppositeBoard = getOppositePlayer().getBoard();
        Fleet oppositeFleet = getOppositePlayer().getFleet();
        printer.printShootingInfo(currentPlayer.getName());
        printer.printBoard(oppositeBoard.toString(false), oppositeBoard.getSize());
        printer.printFleet(oppositeFleet.getAllExistingCrafts().toString());
        printer.printShootInstruction(currentPlayer.getName());
        if (!currentPlayer.shoot(oppositeBoard)) {
            currentGameStatus = GameStatus.QUIT;
        }
        printer.printBoard(oppositeBoard.toString(false), oppositeBoard.getSize());
    }


    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    private Player getOppositePlayer() {
        return currentPlayerIndex == 0 ? players[1] : players[0];
    }

    private void close() {
        printer.printBattle();
        printer.printGameOver(getOppositePlayer().getName());
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public GameStatus getCurrentGameStatus() {
        return currentGameStatus;
    }
}