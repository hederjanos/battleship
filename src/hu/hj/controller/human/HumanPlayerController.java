package hu.hj.controller.human;


import hu.hj.controller.Controller;
import hu.hj.controller.command.AddCommand;
import hu.hj.controller.command.ShotCommand;
import hu.hj.exceptions.io.BattleshipIOException;

import java.io.BufferedReader;

public interface HumanPlayerController extends Controller {

    void setReader(BufferedReader bufferedReader);

    AddCommand getNextCraft() throws BattleshipIOException;

    ShotCommand getNextShot();

}
