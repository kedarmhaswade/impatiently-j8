package practice;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by kmhaswade on 9/3/16.
 */
public class NonuniformDistribution {

    private final int[] numbers;
    private final double[] probabilities;
    private final Random random;
    private final double[] acc;

    /**
     * Constructs a nonuniform distribution. The sum of elements of probabilities must be 1.0d. numbers.length ==
     * probabilities.length.
     * @param numbers
     * @param probabilities
     */
    public NonuniformDistribution(int[] numbers, double[] probabilities) {
        this.numbers = numbers;
        this.probabilities = probabilities;
        this.random = new Random();
        int len = probabilities.length;
        acc = new double[len];
        acc[0] = probabilities[0];
        for (int i = 1; i < len; i++) {
            acc[i] = acc[i - 1] + probabilities[i];
        }
        assert acc[len - 1] >= 1.0d; // required for correctness
//        System.out.println(Arrays.toString(numbers));
//        System.out.println(Arrays.toString(acc));
    }


    /** Returns the <b> index of the </b>next integer in {@linkplain #numbers} that has a probability in proportion
     * with the distribution.
     *
     * @return the next random number
     */
    public int nextIndex() {
        double p = random.nextDouble(); // returns a fraction that is guaranteed to be < 1.0d.
        int idx = Arrays.binarySearch(acc, p);
//        System.out.println("bs returns index: " + idx + " for: " + p);
        return Math.abs(idx) - 1;
    }
    public int next() {
        return numbers[nextIndex()];
    }
}
