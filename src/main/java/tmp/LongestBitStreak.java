package tmp;

/**
 * Created by kedar on 3/5/17.
 */
public class LongestBitStreak {

    /**
     * Returns the longest <i>streak</i> of the given bit b in given number n. A streak is
     * defined as a consecutive sequence of the given bit.
     * @param n the number to find streak in
     * @param b the bit whose streak is to be found
     * @return the longest streak
     */
    static int get(long n, int b) {
        if (b != 0 && b != 1)
            throw new IllegalArgumentException("second arg: (" + b + ") must be 0 or 1");
        int streak = 0, maxStreak = 0;
        for (int i = 0; i < 64; i++) {
            if ((n & 1) == b) {
                streak += 1;
                maxStreak = Math.max(streak, maxStreak);
            } else {
                streak = 0;
            }
            n >>= 1;
        }
        return maxStreak;
    }
}
