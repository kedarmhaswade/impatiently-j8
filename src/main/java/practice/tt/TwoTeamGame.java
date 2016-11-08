package practice.tt;

import java.util.Random;

import static practice.tt.TwoTeamGame.State.DONE;
import static practice.tt.TwoTeamGame.State.NEW;
import static practice.tt.TwoTeamGame.State.WIP;

/**
 * Created by kedar on 11/8/16.
 */
public class TwoTeamGame {
    private final Random random;

    public enum State {
        NEW,
        WIP,
        DONE
    }

    private final Team a;
    private final Team b;
    private final int winningPoints;
    private GameScore score; // mutable, guarded by this.
    State state; // mutable, guarded by this.

    public TwoTeamGame(Team a, Team b, int winningPoints) {
        this.a = a;
        this.b = b;
        this.winningPoints = winningPoints;
        this.random = new Random();
        this.score = GameScore.init(a, b, random.nextBoolean() ? a : b);
        this.state = NEW;
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        this.state = newState;
    }

    public boolean isOver() {
        Integer serving = score.getScore().get(score.getServingTeam());
        Integer receiving = score.getScore().get(score.getReceivingTeam());
        return (serving >= winningPoints ||
                receiving >= winningPoints)
                &&
                (Math.abs(serving - receiving) >= 2);
    }

    public GameScore getScore() {
        return score;
    }

    public void setScore(GameScore score) {
        this.score = score;
    }

    public void start(ScoreListener listener) {
        this.setState(WIP);
        while (!isOver()) {
            Team rallyWinner = simulateRally();
            GameScore newScore = this.score.pointWonBy(rallyWinner);
            System.out.println("**** Rally won by: " + rallyWinner);
            this.setScore(newScore);
            listener.update(newScore);
        }
        this.setState(DONE);
        System.out.println("The winner is: " + score.winningTeam());
    }

    private Team simulateRally() {
        int rallyTime = 0;
        do {
            rallyTime = random.nextInt(5000);
        } while (rallyTime <= 1000);
        try {
            System.out.println("This rally will go on for: " + rallyTime/1000 + " seconds ...");
            Thread.sleep(rallyTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // set the state
            // ignore interruption ?
        }
        return random.nextBoolean() ? this.a : this.b;
    }
}
