package practice;

/**
 * <p>
 * Evaluate: sqrt(1+2(sqrt(1+3(sqrt(1+4(sqrt...))))))
 * </p>
 */
public class RamanujanSum {
    static double calculate(int n, int i) {
        if (n == i)
            return 1.0;
        return Math.sqrt(1 + (i + 1) * calculate(n, i + 1));
    }

    public static void main(String[] args) {
        System.out.println(calculate(100, 1));
    }
}

