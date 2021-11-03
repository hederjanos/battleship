package hu.hj.game;

import hu.hj.constants.GameType;
import hu.hj.exceptions.io.InvalidMenuPoint;

import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final Printer printer = new Printer();
    private MenuItem currentMenu;
    private GameBuilder gameBuilder;

    public Menu() {
        initializeDefaultGameBuilder();
        currentMenu = new MenuBuilder(scanner, printer, gameBuilder).build();
    }

    private void initializeDefaultGameBuilder() {
        gameBuilder = new GameBuilder(GameType.PVC);
    }

    public void run() {
        printer.printGreeting();
        currentMenu.run();
        boolean run = true;
        int actionNumber;
        while (run) {
            try {
                actionNumber = getMenuPoint();
                currentMenu = currentMenu.handleChoice(actionNumber);
                if (currentMenu == null) {
                    run = false;
                    printer.printGoodbye();
                    continue;
                }
                if (currentMenu.getTask() == null) {
                    currentMenu = currentMenu.getParent();
                }
                if (currentMenu.getChildren().isEmpty()) {
                    currentMenu.run();
                    Optional<MenuItem> newMenuItem = Optional.ofNullable(currentMenu.getParent().getParent());
                    currentMenu = newMenuItem.orElse(currentMenu.getParent());
                }
                currentMenu.run();
            } catch (InvalidMenuPoint e) {
                System.out.println(e.getMessage());
                currentMenu.run();
            }
        }
    }

    private int getMenuPoint() throws InvalidMenuPoint {
        String menuPoint = scanner.nextLine();
        try {
            return Integer.parseInt(menuPoint);
        } catch (NumberFormatException e) {
            throw new InvalidMenuPoint(menuPoint);
        }
    }
}