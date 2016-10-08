package practice;

import java.util.*;

/**
 * Created by kmhaswade on 3/12/16.
 */
public class SubsetSum {
    public static void main(String[] args) {
        subsetHasSum(new int[] {14175, 15055, 16616, 17495, 18072, 19390, 19731, 22161, 23320, 23717, 26343, 28725,
                29127, 32257, 40020, 41867, 43155, 46298, 56734, 57176, 58306, 61848, 65825, 66042, 68634, 69189,
                72936, 74287, 74537, 81942, 82027, 82623, 82802, 82988, 90467, 97042, 97507, 99564}, 1_000_000);
    }
    static void subsetHasSum(int[] set, int sum) {
        // basic validations
        Arrays.sort(set);
        System.out.println(Arrays.toString(set));
        Map<Integer, Set<Integer>> takenBy = new HashMap<>(sum + 1);
        for (int i = 0; i < sum + 1; i ++)
            takenBy.put(i, new HashSet<>(set.length));
        int[] sums = new int[sum + 1]; // reachable sums using the available elements in the _set_.
        int[] bp = new int[sum + 1]; // array of backpointers, each _element_ is an _index_ in sums array
        for (int i = 0; i < set.length && set[i] <= sum; i++) {
            sums[set[i]] = set[i]; //e.g. sums[5] = 5
            //e.g. takenBy{5: => [5]}; maintains the numbers that make a given sum
            boolean added = takenBy.get(set[i]).add(set[i]);
            assert added == true : set[i] + " should be added to the sum for i: " + i;
        }
        //bottom up
        for (int i = set[0] + 1; i <= sum; i ++) {
            for (int j = 0; j < set.length; j ++) {
                if (sums[i] == i)
                    break; // we already have an element in the set that is same as sums[i]
                int k = i - set[j];
                if (k < 0)
                    break; // inner loop should end because the index is going to be negative for each subsequent j
                if (sums[k] != 0 && !takenBy.get(sums[k]).contains(set[j])) {
                    // found an element that is not taken!
                    assert sums[k] + set[j] == i;
                    sums[i] = i;
                    takenBy.get(sums[i]).addAll(takenBy.get(sums[k])); // remember all previous ones that give sums[k]
                    takenBy.get(sums[i]).add(set[j]); // and the new one to get from sums[k] to sums[i]
                    bp[i] = k;
                    break; // no need to test with other set elements
                }
            }
        }
        if (sums[sum] != 0) {
//            System.out.println(Arrays.toString(sums));
//            System.out.println(Arrays.toString(bp));
            System.out.println("Yes, it is possible!");
            System.out.print(sum + " = ");
            int i = sum;
            while (true) {
                System.out.print(sums[i] - bp[i]);
                System.out.print(" + ");
                if (bp[bp[i]] == 0) {
                    System.out.print(bp[i]);
                    break;
                }
                i = bp[i];
            }
            System.out.println();
        } else
            System.out.println("No subset of given set sums up to: " + sum);
    }
}
