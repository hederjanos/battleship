package hu.hj.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInterface {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getReader() {
        return bufferedReader;
    }

}
