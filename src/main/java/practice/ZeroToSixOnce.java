package practice;

public class ZeroToSixOnce {
    public static void main(String[] args) {
        m();
    }
    static void m() {
        int a, b, c, d, e, f, g;
        for (a = 0; a <= 6; a++)
            for (b = 0; b <= 6; b++) {
                if (b == a) continue;
                for (c = 0; c <= 6; c++) {
                    if (c == b || c == a) continue;
                    for (d = 0; d <= 6; d++) {
                        if (d == c || d == b || d == a) continue;
                        for (e = 0; e <= 6; e++) {
                            if (e == d || e == c || e == b || e == a) continue;
                            for (f = 0; f <= 6; f++) {
                                if (f == e || f == d || f == c || f == b || f == a) continue;
                                for (g = 0; g <= 6; g++) {
                                    if (g == f || g == e || g == d || g == c || g == b || g == a) continue;
                                    if (a * (10*f + g) == (10*b + c) * (10*d + e)) {
                                        System.out.println(a + "/" + b + "" + c + "=" + d + "" + e + "/" + f + "" + g);
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}
