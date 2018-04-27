package tmp;

/**
 * <p>
 * Cheating to solve a brilliant brilliant.org problem!
 * <pre>
 *     AB
 *   -
 *     CD
 *    ---
 *     EF
 *   +
 *     GH
 *    ---
 *    PPP
 * </pre>
 * </p>
 */
public class BasicCryptarithm {
    public static void main(String[] args) {
        int s = 0;
        for (int ab = 98; ab >= 12; ab--) {
            int a = ab / 10;
            int b = ab % 10;
            if (!uniqueAndGreaterThan1(a, b)) {
                continue;
            }
            for (int cd = 98; cd >= 12; cd--) {
                    int c = cd / 10;
                    int d = cd % 10;
                if (!uniqueAndGreaterThan1(a, b, c, d)) {
                    continue;
                }
                for (int gh = 98; gh >= 12; gh--) {
                    int ef = ab - cd;
                    int e = ef / 10;
                    int f = ef % 10;
                    int g = gh / 10;
                    int h = gh % 10;
                    if (uniqueAndGreaterThan1(a, b, c, d, e, f, g, h) && (ef + gh == 111)) {
                        System.out.println(ab + " - " + cd + " = " + (ab - cd) + " + " + gh + " = 111");
                    }
                }
            }
        }
    }

    static boolean uniqueAndGreaterThan1(int... ints) {
        if (ints.length <= 1) {
            return true;
        }
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[i] <= 1 || ints[j] <= 1) {
                    return false;
                }
                if (ints[i] == ints[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
