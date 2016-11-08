package practice.tt;

import practice.tt.TwoTeamGame.State;

import java.util.Random;

import static java.lang.Thread.State.*;
import static practice.tt.TwoTeamGame.State.WIP;

/**
 * Created by kedar on 11/8/16.
 */
public class SimulatorThread extends Thread {


    private final TwoTeamGame game;
    private final ScoreListener listener;
    public SimulatorThread(TwoTeamGame twoTeamGame, ScoreListener listener) {
        super("table-tennis-game-playing-thread");
        super.setDaemon(false);
        this.game = twoTeamGame;
        this.listener = listener;
    }

    @Override
    public void run() {
        Random r = new Random(1234); // predictable simulation
        game.setState(WIP);
        while (!game.isOver()) {
        }
    }
}
