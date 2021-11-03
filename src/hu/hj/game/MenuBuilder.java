package hu.hj.game;

import hu.hj.constants.*;

import java.util.Scanner;

public class MenuBuilder {

    private final Scanner scanner;
    private final Printer printer;
    private GameBuilder gameBuilder;
    private Game game;

    public MenuBuilder(Scanner scanner, Printer printer, GameBuilder gameBuilder) {
        this.scanner = scanner;
        this.printer = printer;
        this.gameBuilder = gameBuilder;
    }

    public MenuItem build() {

        //___________________Main menu___________________

        MenuItem mainMenu = new MenuItem("main");
        mainMenu.setTask(() -> printer.printMenuItem(mainMenu.toString()));

        MenuItem setupMenu = new MenuItem(mainMenu, "setup game");
        setupMenu.setTask(() -> printer.printMenuItem(setupMenu.toString()));
        mainMenu.addChild(setupMenu);

        MenuItem newGame = new MenuItem(mainMenu, "new/resume game");
        newGame.setTask(() -> {
            if (game != null && game.getCurrentGameStatus() == GameStatus.GAME_OVER) {
                gameBuilder = new GameBuilder(GameType.PVC);
            }
            gameBuilder.addController(scanner);
            game = gameBuilder.getGame();
            game.setPrinter(printer);
            game.run();
        });
        mainMenu.addChild(newGame);

        MenuItem quit = new MenuItem(mainMenu, "quit");
        mainMenu.addChild(quit);

        //___________________Setup menu__________________

        //________________Setup game type________________

        MenuItem setupGameType = new MenuItem(setupMenu, "setup game type");
        setupGameType.setTask(() -> printer.printMenuItem(setupGameType.toString()));
        setupMenu.addChild(setupGameType);

        MenuItem setupPVP = new MenuItem(setupGameType, "human versus human");
        setupPVP.setTask(() -> {
            gameBuilder.setPlayerBuilders(GameType.PVP);
            gameBuilder.initializeDefaultSettings(GameType.PVP);
            printer.printSetInfo(gameBuilder.getGameType().getClass().getSimpleName(), gameBuilder.getGameType().getName());
        });
        setupGameType.addChild(setupPVP);

        MenuItem setupPVC = new MenuItem(setupGameType, "human versus computer");
        setupPVC.setTask(() -> {
            gameBuilder.setPlayerBuilders(GameType.PVC);
            gameBuilder.initializeDefaultSettings(GameType.PVC);
            printer.printSetInfo(gameBuilder.getGameType().getClass().getSimpleName(), gameBuilder.getGameType().getName());
        });
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
}