package hu.hj.game;

import hu.hj.exceptions.io.InvalidMenuPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    private final Scanner scanner;
    private final Printer printer;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.printer = new Printer();
    }

    public void run() {
        printer.printGreeting();
        List<String> names = getPlayerNames();
        MenuItem menu = new MenuBuilder(scanner, printer, names).build();
        menu.run();
        boolean run = true;
        int actionNumber;
        while (run) {
            try {
                actionNumber = getMenuPoint();
                menu = menu.handleChoice(actionNumber);
                if (menu == null) {
                    run = false;
                    printer.printGoodbye();
                    continue;
                }
                if (menu.getTask() == null) {
                    menu = menu.getParent();
                }
                if (menu.getChildren().isEmpty()) {
                    menu.run();
                    Optional<MenuItem> newMenuItem = Optional.ofNullable(menu.getParent().getParent());
                    menu = newMenuItem.orElse(menu.getParent());
                }
                menu.run();
            } catch (InvalidMenuPoint e) {
                printer.printExceptionMessage(e);
                menu.run();
            }
        }
        closeResources();
    }

    private List<String> getPlayerNames() {
        printer.printAddYourName();
        String playerName = scanner.nextLine();
        List<String> names = new ArrayList<>();
        names.add(playerName);
        names.add("defaultPlayer");
        return names;
    }

    private int getMenuPoint() throws InvalidMenuPoint {
        String menuPoint = scanner.nextLine();
        try {
            return Integer.parseInt(menuPoint);
        } catch (NumberFormatException e) {
            throw new InvalidMenuPoint(menuPoint);
        }
    }

    private void closeResources() {
        scanner.close();
        printer.close();
    }
}