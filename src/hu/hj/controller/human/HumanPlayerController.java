package hu.hj.controller.human;


import hu.hj.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;

public interface HumanPlayerController extends Controller {

    void setReader(BufferedReader bufferedReader);

    String getNextCraft() throws IOException;

    void getNextHit();

}
