package hu.hj.controller.strategy;


import hu.hj.board.Board;
import hu.hj.controller.Controller;

public interface Strategy extends Controller {

    void getNextCraft(Board board);

    void getNextShot(Board board);
}
