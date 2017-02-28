package tmp;

/**
 * Created by kedar on 2/18/17.
 */
public class ChangePuzzle {

    public static void main(String[] args) {
        for (int h = 0; h <= 10; h++) {
            for (int q = 0; q <= 20; q++) {
                for (int d = 0; d <= 50; d++) {
                    for (int n = 0; n <= 100; n++) {
                        for (int p = 0; p <= 500; p++) {
                            int v = 50 * h + 25 * q + 10 * d + 5 * n + p;
                            if (v == 500 && !is(100, h, q, d, n, p)) {
                                System.out.println(h + ", " + q + ", " + d + ", " + n + ", " + p);
                            }
                        }
                    }
                }
            }
        }

    }

    private static boolean is(int sum, int H, int Q, int D, int N, int P) {
        for (int h = 0; h <= H; h++) {
            for (int q = 0; q <= Q; q++) {
                for (int d = 0; d <= D; d++) {
                    for (int n = 0; n <= N; n++) {
                        for (int p = 0; p <= P; p++) {
                            int v = 50 * h + 25 * q + 10 * d + 5 * n + p;
                            if (v == sum) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
