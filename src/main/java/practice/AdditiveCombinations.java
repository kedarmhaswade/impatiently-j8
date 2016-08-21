package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *     Given a set of points (integers), find the number of ways in which they can be added to make up a given score.
 *     For example, if the points are 1, 2, 3, a score of 5 can be made in 13 different ways: (1, 1, 1, 1, 1),
 *     (1, 1, 1, 2), (1, 1, 2, 1), (1, 2, 1, 1), (1, 2, 2), (1, 1, 3), (1, 3, 1), (2, 1, 1, 1), (2, 1, 2), (2, 2, 1),
 *     (2, 3), (3, 1, 1), (3, 2).
 * </p>
 * <p>
 *     Clearly a DP problem; with memoization and bottom-up approach this boils down to a processing that is
 *     similar to that of the popular coin change problem.
 * </p>
 * Created by kmhaswade on 8/20/16.
 */
public class AdditiveCombinations {

    /**
     * Implements a solution using a bottom-up, iterative approach.
     * @param set a set of points per attempt
     * @param score a score to get
     * @return number of ways to get to a score given the set of points per shot
     */
    static Integer countBottomUp(Set<Integer> set, Integer score) {
        List<Integer> xs = new ArrayList<>(set);
        xs.sort(null);
        List<Integer> comb = new ArrayList<>(score + 1); // just for clarity
        for (int i = 0; i <= score; i++) {
            comb.add(0);
        }
        for (int x : xs)
            if (x <= score)
                comb.set(x, 1);
        for (int i = 0; i <= score; i++) {
            int total = comb.get(i);
            for (Integer x : xs) {
                int idx = i - x;
                if (idx < 0)
                    break;
                total += comb.get(idx);
            }
            comb.set(i, total);
        }
        return comb.get(score);
    }

    static int countTopDown(Set<Integer> ps, int score) {
        int [] points = new int[ps.size()];
        int i = 0;
        for (int p : ps)
            points[i++] = p;
        Arrays.sort(points);
        Integer[] nWays = new Integer[score + 1];
        return countTopDown(points, nWays, score);
    }
    static int countTopDown(int[] points, Integer[] nWays, int score) {
        if (nWays[score] != null)
            return nWays[score];
        int total = 0;
        for (int pointsPerShot : points) {
            int prevScore = score - pointsPerShot;
            if (prevScore < 0)
                break; // sorted points, so other (higher) values can be skipped
            if (prevScore == 0) {
                total += 1;
                continue;
            }
            Integer nWaysPrevScore = nWays[prevScore];
            if (nWaysPrevScore == null) {
                nWaysPrevScore = countTopDown(points, nWays, prevScore);
                nWays[prevScore] = nWaysPrevScore;
            }
            total += nWaysPrevScore;
        }
        nWays[score] = total;
        return total;
    }
}
