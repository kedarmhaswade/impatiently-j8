package tmp;

/**
 * <p>Another con attempt to solve a basic cryptarithm </p>
 */
public class ClosestTo1000 {
    public static void main(String[] args) {
        int l = 987;
        while (l >= 123) {
            boolean success = doInner(l);
            if (success) {
                break;
            }
            System.out.println("no success with: l = " + l);
            l -= 1;
        }
    }

    private static boolean doInner(int l) {
        int s = 123;
        int a, b, c, d, e, f, g, h, i, m;
        boolean success = false;
        do {
            m = l - s;
            a = s / 100;
            b = (s % 100) / 10;
            c = (s % 100) % 10;
            d = m / 100;
            e = (m % 100) / 10;
            f = (m % 100) % 10;
            g = l / 100;
            h = (l % 100) / 10;
            i = (l % 100) % 10;
            if (a == 0 || b == 0 || c == 0 || d == 0 || e == 0 || f == 0 || g == 0 || h == 0 || i == 0) {
                s += 1;
                continue;
            }
            if (unique(a, b, c, d, e, f, g, h, i)) {
                success = true;
                break;
            }
            s += 1;
        } while (m > 0);
        if (success) {
            System.out.printf("%d + %d = %d%n", s, m, l);
        }
        return success;
    }

    static boolean unique(int... a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
