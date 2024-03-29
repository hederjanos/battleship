package hu.hj.game;

import hu.hj.constants.*;
import hu.hj.director.strategy.Strategy;
import hu.hj.player.ComputerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuBuilder {

    private final Scanner scanner;
    private final Printer printer;
    private final List<String> playerNames;
    private GameBuilder gameBuilder;
    private Game game;

    public MenuBuilder(Scanner scanner, Printer printer, List<String> playerNames) {
        this.scanner = scanner;
        this.printer = printer;
        this.playerNames = playerNames;
    }

    public MenuItem build() {

        //___________________Main menu___________________

        MenuItem mainMenu = new MenuItem("main");
        mainMenu.setTask(() -> printer.printMenuItem(mainMenu.toString()));

        MenuItem setupMenu = new MenuItem(mainMenu, "setup game");
        setupMenu.setTask(() -> setUp(playerNames, setupMenu));
        mainMenu.addChild(setupMenu);

        MenuItem newGame = new MenuItem(mainMenu, "new game");
        newGame.setTask(() -> newGame(playerNames));
        mainMenu.addChild(newGame);

        MenuItem resumeGame = new MenuItem(mainMenu, "resume game");
        resumeGame.setTask(this::resumeGame);
        mainMenu.addChild(resumeGame);

        MenuItem quit = new MenuItem(mainMenu, "quit");
        mainMenu.addChild(quit);

        //__________________Setup menu___________________

        //________________Setup game type________________

        MenuItem setupGameType = new MenuItem(setupMenu, "setup game type");
        setupGameType.setTask(() -> printer.printMenuItem(setupGameType.toString()));
        setupMenu.addChild(setupGameType);

        MenuItem setupPVP = new MenuItem(setupGameType, "human versus human");
        setupPVP.setTask(() -> setUpGameType(GameType.PVP, updatePlayerNames()));
        setupGameType.addChild(setupPVP);

        MenuItem setupPVC = new MenuItem(setupGameType, "human versus computer");
        setupPVC.setTask(() -> setUpGameType(GameType.PVC, gameBuilder.getPlayerNames()));
        setupGameType.addChild(setupPVC);

        //________________Setup Board type________________

        MenuItem setupBoardType = new MenuItem(setupMenu, "setup board type");
        setupBoardType.setTask(() -> printer.printMenuItem(setupBoardType.toString()));
        setupMenu.addChild(setupBoardType);

        MenuItem setUpSimpleBoard = new MenuItem(setupBoardType, "simple board");
        setUpSimpleBoard.setTask(() -> gameBuilder.addBoard(BoardType.SIMPLE));
        setupBoardType.addChild(setUpSimpleBoard);

        //________________Setup Fleet type________________

        MenuItem setupFleetType = new MenuItem(setupMenu, "setup fleet type");
        setupFleetType.setTask(() -> printer.printMenuItem(setupFleetType.toString()));
        setupMenu.addChild(setupFleetType);

        MenuItem setUpStandardFleet = new MenuItem(setupFleetType, "standard fleet");
        setUpStandardFleet.setTask(() -> gameBuilder.addFleet(FleetType.STANDARD));
        setupFleetType.addChild(setUpStandardFleet);

        //________________Setup Difficulty________________

        MenuItem setupDifficulty = new MenuItem(setupMenu, "setup difficulty");
        setupDifficulty.setTask(() -> printer.printMenuItem(setupDifficulty.toString()));
        setupMenu.addChild(setupDifficulty);

        MenuItem setUpEasy = new MenuItem(setupDifficulty, "easy");
        setUpEasy.setTask(() -> gameBuilder.addStrategy(Difficulty.EASY));
        setupDifficulty.addChild(setUpEasy);

        MenuItem setUpHard = new MenuItem(setupDifficulty, "hard");
        setUpHard.setTask(() -> gameBuilder.addStrategy(Difficulty.HARD));
        setupDifficulty.addChild(setUpHard);

        MenuItem back = new MenuItem(setupMenu, "back to main");
        setupMenu.addChild(back);

        setupMenu.addChild(new MenuItem(setupMenu, "quit"));

        return mainMenu;
    }

    private void setUp(List<String> playerNames, MenuItem setupMenu) {
        printer.printMenuItem(setupMenu.toString());
        if (gameBuilder == null) {
            gameBuilder = new GameBuilder(GameType.PVC, playerNames);
        }
    }

    private void newGame(List<String> playerNames) {
        if (gameBuilder == null) {
            gameBuilder = new GameBuilder(GameType.PVC, playerNames);
        } else if (game != null) {
            refreshGameBuilderWithUserSettings(playerNames);
        }
        runNewGame();
    }

    private void refreshGameBuilderWithUserSettings(List<String> playerNames) {
        GameBuilder newGameBuilder = new GameBuilder(GameType.PVC, playerNames);
        newGameBuilder.addBoard(gameBuilder.getSecondPlayerBuilder().getPlayer().getBoard().getBoardType());
        newGameBuilder.addFleet(gameBuilder.getSecondPlayerBuilder().getPlayer().getFleet().getFleetType());
        if (gameBuilder.getSecondPlayerBuilder().getPlayer() instanceof ComputerPlayer) {
            Strategy strategy = ((ComputerPlayer) gameBuilder.getSecondPlayerBuilder().getPlayer()).getStrategy();
            gameBuilder.addStrategy(strategy.getDifficulty());
        }
        gameBuilder = newGameBuilder;
    }

    private void resumeGame() {
        if (game == null) {
            printer.printGameNotExist();
        } else if (game.getCurrentGameStatus() == GameStatus.GAME_OVER) {
            refreshGameBuilderWithUserSettings(playerNames);
            runNewGame();
        } else {
            runExistingGame();
        }
    }

    private void runExistingGame() {
        gameBuilder.addController(scanner);
        game.setPrinter(printer);
        game.run();
    }

    private void setUpGameType(GameType gameType, List<String> playerNames) {
        gameBuilder.setPlayerBuilders(gameType, playerNames);
        gameBuilder.initializeDefaultSettings(gameType);
        printer.printSetInfo(gameBuilder.getGameType().getClass().getSimpleName(), gameBuilder.getGameType().getName());
    }

    private void runNewGame() {
        gameBuilder.addController(scanner);
        game = gameBuilder.buildGame();
        game.setPrinter(printer);
        game.run();
    }

    private List<String> updatePlayerNames() {
        String playerName = getOpponentPlayerName();
        List<String> names = new ArrayList<>();
        names.add(gameBuilder.getPlayerNames().get(0));
        names.add(playerName);
        return names;
    }

    private String getOpponentPlayerName() {
        printer.printAddYourOpponentName();
        return scanner.nextLine();
    }
}