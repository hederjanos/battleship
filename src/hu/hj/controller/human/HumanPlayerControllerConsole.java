package hu.hj.controller.human;

import java.io.BufferedReader;
import java.io.IOException;

public class HumanPlayerControllerConsole implements HumanPlayerController {

    private BufferedReader bufferedReader;

    public void setReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String getNextCraft() throws IOException {
        System.out.println("\nAdd:");
        return bufferedReader.readLine();
    }

    @Override
    public void getNextHit() {
    }

}
