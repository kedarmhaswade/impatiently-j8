package practice.tt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kedar on 11/8/16.
 */
public class GameScore {

    private final Map<Team, Integer> score;
    private final Team servingTeam;

    public GameScore(Map<Team, Integer> score, Team servingTeam) {
        if (score == null || score.size() != 2)
            throw new IllegalArgumentException("the score is for two teams");
        this.score = Collections.unmodifiableMap(score);
        this.servingTeam = servingTeam;
    }

    public Team winningTeam() {
        int max = Integer.MIN_VALUE;
        Team winningTeam = null;
        for (Map.Entry<Team, Integer> entry : score.entrySet()) {
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
                winningTeam = entry.getKey();
            }
        }
        return winningTeam;
    }
    public Map<Team, Integer> getScore() {
        return score;
    }
    public Team getServingTeam() {
        return servingTeam;
    }
    public Team getReceivingTeam() {
        for (Team t : score.keySet())
            if (! t.equals(servingTeam))
                return t;
        throw new AssertionError("impossible, there must be a receiving team!");
    }
    @Override
    public String toString() {
        return  "scores: " + score.toString() + "\n" +
                "servingTeam: " + servingTeam + "\n" +
                "winningTeam: " + winningTeam();
    }

    public GameScore pointWonBy(Team w) {
        // every winning shot increments w's score by one
        Integer newPoints = this.score.get(w) + 1;
        Map<Team, Integer> newScore = new HashMap<>(score);
        newScore.put(w, newPoints);
        boolean sum5 = newScore.values().stream().reduce(Integer::sum).get() % 5 == 0;
        Team newServingTeam = sum5 ? this.getReceivingTeam() : this.servingTeam;
        if (! this.servingTeam.equals(newServingTeam)) {
            System.out.println("service change ........................");
        }
        return new GameScore(newScore, newServingTeam);
    }

    public static GameScore init(Team a, Team b, Team servingTeam) {
        if (! servingTeam.equals(a) && ! servingTeam.equals(b))
            throw new IllegalArgumentException("serving team must be one of the two, but it is not");
        Map<Team, Integer> sc = new HashMap<>(2);
        sc.put(a, 0);
        sc.put(b, 0);
        return new GameScore(sc, servingTeam);
    }
}
