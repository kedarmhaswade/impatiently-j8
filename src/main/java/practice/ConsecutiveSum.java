package practice;

import com.google.common.collect.Range;

import java.util.List;

import static com.google.common.collect.Range.closed;

/**
 * <p> Given a long integer, num, find the number of ways to represent it as a sum of two or more consecutive positive
 * integers. For example:
 *
 * <p> If num = 15, then there are three such ways: (1 + 2 + 3 + 4 + 5) = (4 + 5 + 6) = (7 + 8) = 15. </p>
 * <p> If num = 2, then there are zero such ways. </p>
 *
 *
 * <p>Complete the method consecutive. It has one parameter: a long integer named num. The method must return an integer
 * denoting the number of ways to represent num as a sum of two or more consecutive positive integers. </p>
 *
 * <p> What will you do to print the numbers that represent the way the sum is achieved? </p>
 */
public class ConsecutiveSum {

    /**
     * <p> Initially I thought I could use dynamic programming, although the "overlapping subproblems" don't appear
     * immediately because one consecutive number arrangement does not lead to another one; at least there's nothing
     * obvious.</p>
     *
     * <p> About thinking more, talking to people, it was revealed that this could be solved using an invariant-based
     * approach. The interesting thing about this is it uses O(num) time where num is the integer to reach as a sum of
     * consecutive positive integers.</p>
     *
     * <p> The algorithm however, is not easy to see at first sight.</p>
     * @param num
     * @return
     */
    static int consecutive(long num, List<Range<Integer>> ranges) {
        if (num < 3)
            return 0;
        int ways = 0;
        int i = 1, j = 2;
        long cur = i + j;
        while (j < num) {
            if (cur == num) {
                ways += 1;
                boolean add = ranges.add(closed(i, j)); // return value ignored
                j += 1;
                cur += j;
            } else if (cur < num) {
                j += 1;
                cur += j;
            } else {
                cur -= i;
                i += 1;
            }
        }
        return ways;
    }

    public static void main(String[] args) {

    }
}
