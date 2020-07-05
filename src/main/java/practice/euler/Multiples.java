package practice.euler;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class Multiples {

    static long add(int limit) {
        int n = 3;
        long sum = 0;
        while (n < limit) {
            if (n % 3 == 0) {
                sum += n;
            } else if (n % 5 == 0) {
                sum += n;
            } else {
                // do nothing
            }
            n += 1;
            // naive algorithm that just iterates, we could "generate" instead
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(1000));
    }
}
