package practice;

/** <p>
 * https://www.careercup.com/question?id=5667564572114944
 * </p>
 * <p>
 *     Given an array of numbers, find the longest alternating subsequence.
 *     That is, a subsequence [a1, a2, a3, ..., ak] where a1 &gt; a2, a3 &lt; a2, a4 &gt; a3, ... or vice versa (Graphically looks like /\/\/\... or \/\/\/\....
 * </p>
 * Created by केदार on 4/23/16.
 */
public class LongestAlternatingSubarray {
    public static void main(String[] args) {
//        System.out.println(longestAlternatingSubarray(new int[]{-1, 1, -3, 4, -5}));
//        System.out.println(longestAlternatingSubarray(new int[]{-10, 10, -30, 40, 50}));
//        System.out.println(longestAlternatingSubarray(new int[]{0, 1, -2, -3, 3}));
//        System.out.println(longestAlternatingSubarray(new int[]{0, 1, -2, -3, 3, 0, 1}));
//        System.out.println(longestAlternatingSubarray(new int[]{ 1, 7, 4, 9, 2, 5 }));
        System.out.println(longestAlternatingSubarray(new int[]{ 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }));
//        System.out.println(longestAlternatingSubarray(new int[]{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }));
//        System.out.println(longestAlternatingSubarray(new int[]{ 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
//                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
//                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
//                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
//                249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }));
    }
    public static int longestAlternatingSubarray(int[] a) {
        if (a.length <= 1)
            return a.length;
        int j = 1;
        while (j < a.length && a[j] == a[j-1])
            j += 1;
        int streak = 1;
        int longestStreak = streak;
        int prevDir = 0;
        for (int i = j; i < a.length; i++) {
            if (alternating(a, i, prevDir))
                streak += 1; // extend the streak
            else
                streak = 1; // fresh streak
            if (streak > longestStreak)
                longestStreak = streak;
            prevDir = a[i] < a[i-1] ? -1 : 1;
        }
        return longestStreak;
    }
    /** Decides if the sequence ending at {@code i} is alternating. */
    private static boolean alternating(int[] a, int i, int prevDir) {
        // i >= 1
        // prevDir is one of 0, -1, 1
        if (prevDir == 0)
            return true;
        if (prevDir == -1 && a[i] > a[i-1]) // we were going down previously and we are going up now
            return true;
        if (prevDir == 1 && a[i] < a[i-1]) // we were going up previously and we are going down now
            return true;
        return false;
    }

//    public static int longestAlternatingSubarray(int[] a) {
//        int streak = 1;
//        int longestStreak = streak;
//        for (int i = 1; i < a.length; i++) {
//            if (alternating(a, i))
//                streak += 1; // extend the streak
//            else
//                streak = 1; // fresh streak
//            if (streak > longestStreak)
//                longestStreak = streak;
//        }
//        return longestStreak;
//    }

//    private static boolean alternating(int[] a, int i) {
//        // i must be >= 1
//        int prev = a[i - 1];
//        int curr = a[i];
//        if (i == a.length - 1) // last
//            return prev != curr;
//        int next = a[i + 1];
//        return (prev > curr && next > curr) || (prev < curr && next < curr);
//    }
}
