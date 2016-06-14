package practice;

/** A problem based on {@linkplain LongestAlternatingSubarray}.
 * <p>
 *     In the case of LongestAlternatingSubarray, we consider the consecutive elements.
 *     A subsequence however, does not need to consist of consecutive elements of the array.
 *     Here, the nature of the problem changes subtly, but significantly. For the subarray case,
 *     we need only look at the direction we were going at the previous element. For the present
 *     case of subsequence however, we must look at all the candidates we have encountered so far
 *     and also use the memory so we can remember the results (i.e. not solve the subproblems
 *     repeatedly, but instead remember their results). Thus, to find the longest subsequence
 *     ending at index i L(i), we need to know the maximum of (L(i-1), L(i-2), ...). If we did
 *     not save L(i-1) however, we easily run into an exponential algorithm. This is where
 *     dynamic programming helps. We maintain these arrays here:
 *     <ol>
 *         <li> The array of lengths of longest subsequences ending at i </li>
 *         <li> The array of directions of longest subsequences ending at i </li>
 *         <li> The (optional) array of back pointers in case we need to find the actual subsequence</li>
 *     </ol>
 * </p>
 * <p>
 *     This is same as the <a href = "https://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493">ZigZag
 *     sequence on Topcoder</a>.
 * </p>
 * Created by kmhaswade on 4/23/16.
 */
public class LongestAlternatingSubsequence {

    public static void main(String[] args) {
        System.out.println(longestAlternatingSubsequence(new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }));
        System.out.println(longestAlternatingSubsequence(new int[] { 1, 7, 4, 9, 2, 5 }));
        System.out.println(longestAlternatingSubsequence(new int[]{ 44 }));
        System.out.println(longestAlternatingSubsequence(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
        System.out.println(longestAlternatingSubsequence(new int[]{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }));
        System.out.println(longestAlternatingSubsequence(new int[]{ 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }));
    }

    private static int longestAlternatingSubsequence(int[] a) {
        if (a.length <= 1)
            return a.length;
        int[] lengths = new int[a.length]; // initialized to all 0's, these are the lengths
        int[] dirs = new int[a.length]; // initialized to all 0's, these are the directions (0: x, 1: y, -1: -y)
        int[] bps = new int[a.length]; // initialized to all 0's, these are the indices
        lengths[0] = 1;
        bps[0] = -1; // there is no back pointer at index 0!
        for (int i = 1; i < a.length; i++) {
            int winningIndex = -1; // invalid, deliberately
            int maxLength = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] < a[j] && dirs[j]  == 1  || // was going up and now going down
                    a[i] > a[j] && dirs[j]  == -1 || // was going down and now going up
                    a[i] != a[j] && dirs[j] == 0) {  // different element
                    // we are alternating!
                    if (lengths[j] > maxLength) {
                        maxLength = lengths[j];
                        winningIndex = j;
                    }
                }
            }
            if (winningIndex != -1) {
                lengths[i] = lengths[winningIndex] + 1;
                dirs[i] = a[winningIndex] < a[i] ? 1 : -1;
                bps[i] = winningIndex;
            } else {
                lengths[i] = 1;
                bps[i] = -1;
            }
        }
        return lengths[a.length - 1];
    }
}