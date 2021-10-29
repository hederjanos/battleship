package hu.hj.controller.human;

import hu.hj.constants.Colour;
import hu.hj.constants.Orientation;
import hu.hj.controller.command.AddCommand;
import hu.hj.controller.command.ShotCommand;
import hu.hj.craft.crafts.Craft;
import hu.hj.craft.fleet.Fleet;
import hu.hj.exceptions.io.BattleshipIOException;
import hu.hj.exceptions.io.InvalidAddCommandFormatException;
import hu.hj.exceptions.io.InvalidCraftNameException;
import hu.hj.exceptions.io.InvalidOrientationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayerControllerConsole implements HumanPlayerController {

    private static final String ADD_CRAFT_REGEXP = "^[a-z]*[\\s][[a-z]]*[\\s][a-z][\\s][\\d]*$";
    private static final char A = 'a';
    private BufferedReader bufferedReader;
    private final Map<String, List<String>> craftDictionary;
    private final Map<String, List<String>> orientationDictionary;

    public HumanPlayerControllerConsole(Fleet fleet) {
        this.craftDictionary = new HashMap<>();
        for (Craft craft : fleet.getAllNotAddedCrafts()) {
            String craftName = craft.getClass().getSimpleName();
            craftDictionary.putIfAbsent(craftName, List.of(craftName.toLowerCase(), craftName.substring(0, 3).toLowerCase()));
        }

        this.orientationDictionary = new HashMap<>();
        for (Orientation orientation : Orientation.values()) {
            String orientationName = orientation.toString();
            orientationDictionary.putIfAbsent(orientationName, List.of(orientationName.toLowerCase(), orientationName.substring(0, 1).toLowerCase()));
        }
    }

    public void setReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public AddCommand getNextCraft() throws BattleshipIOException {
        while (true) {
            try {
                System.out.println("Add a craft to the board.");
                System.out.println("Examples for valid formats: carrier\\car north\\n b 3. Case insensitive.");
                System.out.print("Type your command: ");
                String input = bufferedReader.readLine().toLowerCase();
                patternMatches(input);
                return parseAddCommand(input);
            } catch (IOException e) {
                throw new BattleshipIOException(e.getMessage());
            } catch (InvalidAddCommandFormatException | InvalidCraftNameException | InvalidOrientationException e) {
                System.out.println(Colour.ANSI_RED.getColourCode() + e.getMessage());
                System.out.println(Colour.ANSI_RESET.getColourCode());
            }
        }
    }

    private void patternMatches(String input) throws InvalidAddCommandFormatException {
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
        int[] coordinates = new int[]{xCoordinate, yCoordinate};

        return new AddCommand(craftName, orientationName, coordinates);
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
    public ShotCommand getNextShot() {
        return null;
    }
}