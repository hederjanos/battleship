package hu.hj.director.command;

public abstract class PlayControlCommand {

    protected int[] coordinates;

    protected PlayControlCommand(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int[] getCoordinates() {
        return coordinates;
    }
}