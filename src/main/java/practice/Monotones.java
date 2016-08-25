package practice;

/**
 * <p>
 *     A decimal number is a sequence of decimal digits. The first digit must be nonzero.
 *     A decimal number is a monotone, if D[i] &le; D[i+1]; i &le; 0 &le; |D|-1, D being the array of digits written
 *     in the big-endian way (like we write the numbers).
 * </p>
 * <p>
 *     Given the number of digits, k, find the number of monotones in all k-digit numbers.
 * </p>
 * Created by kmhaswade on 8/23/16.
 */
public class Monotones {
    /**
     * Finds the number of monotones of digit k. Uses constant memory, and O(k) running time (constant factor being 10).
     * @param k the number of digits, must be >= 1
     * @return number of monotones in all k-digit numbers
     */
    static int howMany(int k) {
        if (k <= 1)
            return 0;
        /*
        We use a table of monotones like this:
                          which digit from left ---->
                  |        1          2          3           4 ... k
           digit \|/ 0     0          0          0
                  .  1     0          1          1
                     2     0          2          3
                     3     0          3          6
                     4     0          4          10
                     5     0          5          15
                     6     0          6          21
                     7     0          7          28
                     8     0          8          36
                     9     0          9          45
                sum                   45

           The way to read this table is, if the 2nd digit is 5, there are 5 2-digit monotones (11, 12, 13, 14, 15),
           if the 3rd digit is 3, there are 6 3-digit monotones (113, 123, 133, 223, 233, 333) and so on. We see the
           pattern to fill the kth column of the table. To optimize the space, we maintain only one column
           of the table. Since we already know the number of monotones (sum) of 2 digits (45), we simply initialize
           it as such.
         */
        if (k == 2)
            return 45; // pre-calculated
        int[] m = new int[10];
        for (int i = 0; i < 10; i++)
            m[i] = i;  // this is for 2-digit monotones
        int sum = 0; // summing for each column, although we need the sum of last column only
        for (int j = 3; j <= k; j++) {
            sum = 0;
            m[0] = 0;
            for (int i = 1; i < 10; i++) {
                m[i] = m[i] + m[i - 1];
                sum += m[i];
            }
        }
        return sum;
    }

    private static int howManyRecursive(String prefix, int rem) {
        if (rem == 0) {
//            System.out.println(prefix);
            return 1;
        }
        int length = prefix.length();
        int e = length >= 1 ? prefix.charAt(length - 1) - '0' :  1; // prefix ends in this digit
        int sum = 0;
        for (int i = e; i <= 9; i++) {
            sum += howManyRecursive(prefix + i, rem - 1);
        }
        return sum;
    }
    static int howManyRecursive(int k) {
        return howManyRecursive("", k);
    }
}
