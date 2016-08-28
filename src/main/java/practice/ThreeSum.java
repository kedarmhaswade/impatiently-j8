package practice;

import java.util.Arrays;

/**
 * <p>
 *     Given an array of numbers, are there <b>three</b> numbers (repetitions allowed) that sum up to a given number?
 * </p>
 * <p>
 *     Repetitions are allowed -- this means that one of the entries of the array can be picked more than once.
 *     We can definitely take help from the {@linkplain BasicTwoSum#get(int[], int)}.
 * </p>
 * Created by kmhaswade on 8/27/16.
 */
public class ThreeSum {

    /**
     * Returns true if the given array has a triple (repetitions allowed) that sums up to a given sum, false otherwise.
     * @param a
     * @param sum
     * @param indexes array that should hold the indexes of those elements; reliable only if the method returns true.
     * @return true if a triple exists, false otherwise
     */
    static boolean exist3(int[] a, int sum, int[] indexes) {
        int length = a.length;
        Arrays.sort(a);
        for (int i = 0; i < length; i++) {
            if (exist2(a, i, length, sum - a[i], indexes)) {
                indexes[2] = i;
                return true;
            }
        }
        return false;
    }

    private static boolean exist2(int[] a, int lowInc, int highExc, int sum, int[] indexes) {
        // assumes that the subarray a[lowInc] to a[highExc - 1] is sorted
//        System.out.println("target sum: " + sum);
        int i = lowInc, j = highExc - 1;
        for (; i <= j; ) {
            int f = a[i], s = a[j];
            int tmp = f + s;
//            System.out.println("i: " + i +", j: " + j + ", tmp: " + tmp);
            if (tmp == sum) {
                indexes[0] = i;
                indexes[1] = j;
                return true;
            } else if (tmp < sum) {
                i += 1; // falling short; increase near end
            } else {
                assert tmp > sum;
                j -= 1; // overshooting the target; decrease the far end
            }
        }
        return false;
    }
}
