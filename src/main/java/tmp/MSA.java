package tmp;

/**
 * Created by kedar on 1/28/17.
 */
public class MSA {
    static int getMaxSum(int[] sequence) {
        int [] solution = new int[sequence.length];
        int max = solution[0] = Math.max(0, sequence[0]);

        for (int i = 1; i < sequence.length; i++) {
            solution[i] = Math.max(0, solution[i-1] + sequence[i]);
            max = Math.max(max, solution[i]);
        }
        return max;
    }
    static int getMaxSum1(int[] sequence) {
        int [] solution = new int[sequence.length];
        int max = solution[0] = sequence[0];

        for (int i = 1; i < sequence.length; i++) {
            solution[i] = Math.max(sequence[i], solution[i-1] + sequence[i]);
            max = Math.max(max, solution[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSum(new int[] {-1, -2, -3}));
        System.out.println(getMaxSum(new int[] {-3, -2, -1}));
        System.out.println(getMaxSum1(new int[] {-1, -2, -3}));
        System.out.println(getMaxSum1(new int[] {-3, -2, -1}));
        System.out.println(getMaxSum1(new int[] {-3, 2, 1}));
    }
}
