package practice;

import java.util.Arrays;

/**
 * <p>
 *     In a game, certain winning plays can be made. Each winning play fetches points specified by a points array. Two
 *     teams (A and B) play the game. Given the score of the game (a-b), find the _maximum_ number of times the leader
 *     changed during the game.
 * </p>
 * <p>
 *     An optimization problem that suggests the use of a 2-D leader board for various possible scores given the
 *     points per winning play. We assume the change in leadership only when
 *     <ul>
 *         <li>Unequal scores lead to unequal scores in opposite direction</li>
 *     </ul>
 *     as a result of a winning play by a team. We use a matrix to maintain the maximum.
 * </p>
 * Created by kmhaswade on 8/21/16.
 */
public class LeaderChange {

    // maxTimesCurrentScoreBased is wrong, but keeping it here for the sake of showing the attempt
    static int maxTimesCurrentScoreBased(int aFinal, int bFinal, int[] points) {
        Arrays.sort(points);
        int[][] board = new int[aFinal + 1][bFinal + 1];
        // the board is initialized to all zeros
        for (int aScore = 0; aScore <= aFinal; aScore++) {
            int max = 0;
            for (int bScore = 0; bScore <= bFinal; bScore++) {
                if (aScore > bScore) {
                    for (int play : points) {
                        int prevAScore = aScore - play;
                        if (prevAScore < 0)
                            break;
                        if (prevAScore < bScore) {
                            max = Math.max(max, board[prevAScore][bScore] + 1);
                        }
                    }
                } else if (aScore < bScore) {
                    for (int play : points) {
                        int prevBScore = bScore - play;
                        if (prevBScore < 0)
                            break;
                        if (prevBScore < aScore) {
                            max = Math.max(max, board[aScore][prevBScore] + 1);
                        }
                    }
                } else {
                    assert aScore == bScore;
                    //do nothing, since equal scores does not make aFinal leadership change
                }
                board[aScore][bScore] = max;
            }
        }
        Utils.print(board);
        return board[aFinal][bFinal];
    }
    static int maxTimesAllCases(int aFinal, int bFinal, int[] points) {
        Arrays.sort(points);
        int[][] board = new int[aFinal + 1][bFinal + 1];
        // the board is initialized to all zeros
        for (int aScore = 0; aScore <= aFinal; aScore++) {
            for (int bScore = 0; bScore <= bFinal; bScore++) {
                int max = board[aScore][bScore];
                int changing = aScore;
                for (int play : points) {
                    int prevAScore = changing - play;
                    if (prevAScore < 0)
                        break;
                    if ((prevAScore < bScore && aScore > bScore) || prevAScore > bScore && aScore < bScore) {
                        max = Math.max(max, board[prevAScore][bScore] + 1);
                    } else {
                        max = Math.max(max, board[prevAScore][bScore]);
                    }
                }
                changing = bScore;
                for (int play : points) {
                    int prevBScore = changing - play;
                    if (prevBScore < 0)
                        break;
                    if ((prevBScore < aScore && bScore > aScore) || prevBScore > aScore && bScore < aScore) {
                        max = Math.max(max, board[aScore][prevBScore] + 1);
                    } else {
                        max = Math.max(max, board[aScore][prevBScore]);
                    }
                }
                board[aScore][bScore] = max;
            }
        }
        Utils.print(board);
        return board[aFinal][bFinal];
    }

}
