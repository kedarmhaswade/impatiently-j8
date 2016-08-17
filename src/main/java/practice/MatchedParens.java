package practice;

/**
 * <p>
 *     Given a string of left and right parentheses, find the longest length of the matched parentheses.
 * </p>
 * Created by kmhaswade on 8/14/16.
 */
public class MatchedParens {

    static int longest(char[] c) {
        // this algorithm is not yet verified, although tests passed for it TODO
        int i = 0;
        int len = c.length;
        while (i < len && c[i] != '(')
            i++;
        if (i == len) {
            return -1; // no left parenthesis seen
        }
        // c[i] is the first '('.
        int longest = -1, streak = -1;
        int j = i + 1;
        int indicator = 1;
        int nr = 0;
        while (j < len) {
            char p = c[j];
            if (p == '(') {
                if (indicator >= 0) {
                    indicator += 1;
                } else {
                    indicator = 0; // reset
                }
            } else {
                // assert p == ')'
                indicator -= 1;
                nr += 1;
                if (indicator >= 0) {
                    streak = 2 * nr;
                    longest = Math.max(longest, streak);
                }
            }
            j += 1;
        }
        return longest;
    }
}
