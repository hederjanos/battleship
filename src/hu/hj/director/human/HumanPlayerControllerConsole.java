package hu.hj.director.human;

import hu.hj.constants.Orientation;
import hu.hj.craft.crafts.Craft;
import hu.hj.craft.fleet.Fleet;
import hu.hj.director.command.AddCommand;
import hu.hj.director.command.ShotCommand;
import hu.hj.exceptions.io.InvalidAddCommandFormatException;
import hu.hj.exceptions.io.InvalidCraftNameException;
import hu.hj.exceptions.io.InvalidOrientationException;
import hu.hj.exceptions.io.InvalidShotCommandFormatException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayerControllerConsole implements HumanPlayerController {

    private static final String ADD_CRAFT_REGEXP = "^[a-z]*[\\s][a-z]*[\\s][a-z][\\s][\\d]*$";
    private static final String SHOT_REGEXP = "^[a-z]*[\\s][\\d]*$";
    private static final char A = 'a';
    private static final String Q = "q";
    private final Scanner scanner;
    private Map<String, List<String>> orientationDictionary;
    private Map<String, List<String>> craftDictionary;

    public HumanPlayerControllerConsole(Scanner scanner) {
        this.scanner = scanner;
        initializeOrientationDictionary();
    }

    private void initializeOrientationDictionary() {
        this.orientationDictionary = new HashMap<>();
        for (Orientation orientation : Orientation.values()) {
            String orientationName = orientation.toString();
            orientationDictionary.putIfAbsent(orientationName, List.of(orientationName.toLowerCase(), orientationName.substring(0, 1).toLowerCase()));
        }
    }

    private void initializeCraftDictionary(Fleet fleet) {
        if (this.craftDictionary == null) {
            this.craftDictionary = new HashMap<>();
            for (Craft craft : fleet.getAllNotAddedCrafts()) {
                String craftName = craft.getClass().getSimpleName();
                craftDictionary.putIfAbsent(craftName, List.of(craftName.toLowerCase(), craftName.substring(0, 3).toLowerCase()));
            }
        }
    }

    @Override
    public AddCommand getNextCraftFromFleet(Fleet fleet) throws InvalidAddCommandFormatException, InvalidCraftNameException, InvalidOrientationException {
        AddCommand addCommand = null;
        initializeCraftDictionary(fleet);
        String input = getCommand();
        if (!isQuit(input)) {
            patternMatchesWithAddRule(input);
            addCommand = parseAddCommand(input);
        }
        return addCommand;
    }

    private boolean isQuit(String input) {
        return input.equals(Q);
    }

    private String getCommand() {
        return scanner.nextLine().toLowerCase();
    }

    private void patternMatchesWithAddRule(String input) throws InvalidAddCommandFormatException {
        Pattern pattern = Pattern.compile(ADD_CRAFT_REGEXP);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidAddCommandFormatException(input);
        }
    }

    private AddCommand parseAddCommand(String input) throws InvalidCraftNameException, InvalidOrientationException {
        String[] commandParts = input.split(" ");
        String craftName = getCraftName(commandParts[0]);
        if (craftName == null) {
            throw new InvalidCraftNameException(commandParts[0]);
        }
        String orientationName = getOrientationName(commandParts[1]);
        if (orientationName == null) {
            throw new InvalidOrientationException(commandParts[1]);
        }
        int xCoordinate = Character.codePointAt(commandParts[2].toCharArray(), 0) - A;
        int yCoordinate = Integer.parseInt(commandParts[3]) - 1;

        return new AddCommand(craftName, orientationName, xCoordinate, yCoordinate);
    }

    private String getCraftName(String input) {
        String craftName = null;
        for (Map.Entry<String, List<String>> stringListEntry : craftDictionary.entrySet()) {
            for (String craftNameVariation : stringListEntry.getValue()) {
                if (craftNameVariation.equals(input)) {
                    craftName = stringListEntry.getKey();
                    break;
                }
            }
        }
        return craftName;
    }

    private String getOrientationName(String input) {
        String orientationName = null;
        for (Map.Entry<String, List<String>> stringListEntry : orientationDictionary.entrySet()) {
            for (String orientationNameVariation : stringListEntry.getValue()) {
                if (orientationNameVariation.equals(input)) {
                    orientationName = stringListEntry.getKey();
                    break;
                }
            }
        }
        return orientationName;
    }

    @Override
    public ShotCommand addNextShot() throws InvalidShotCommandFormatException {
        ShotCommand shotCommand = null;
        String input = getCommand();
        if (!isQuit(input)) {
            patternMatchesWithShotRule(input);
            shotCommand = parseShotCommand(input);
        }
        return shotCommand;
    }

    private ShotCommand parseShotCommand(String input) {
        String[] commandParts = input.split(" ");
        int xCoordinate = Character.codePointAt(commandParts[0].toCharArray(), 0) - A;
        int yCoordinate = Integer.parseInt(commandParts[1]) - 1;
        return new ShotCommand(xCoordinate, yCoordinate);
    }

    private void patternMatchesWithShotRule(String input) throws InvalidShotCommandFormatException {
        Pattern pattern = Pattern.compile(SHOT_REGEXP);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidShotCommandFormatException(input);
        }
    }
}