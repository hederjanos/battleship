package hu.hj.director.command;

public class AddCommand extends PlayControlCommand {

    private final String craftName;
    private final String orientationName;

    public AddCommand(String craftName, String orientationName, int... coordinates) {
        super(coordinates);
        this.craftName = craftName;
        this.orientationName = orientationName;
    }

    public String getCraftName() {
        return craftName;
    }

    public String getOrientationName() {
        return orientationName;
    }

}