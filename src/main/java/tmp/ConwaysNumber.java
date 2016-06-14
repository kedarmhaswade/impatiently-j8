package tmp;

import java.util.Arrays;

/** First problem at <a href="http://blog.pizzahut.com/flavor-news/national-pi-day-math-contest-problems-are-here-2/">
 * rather unfortunate but fun nexus between Pizza Hut and Conway</a>
 * Created by kmhaswade on 3/20/16.
 */
public class ConwaysNumber {

    static void badFind() {
        int[] digits = new int[10];
        for (int d0 = 1; d0 <= 9; d0 += 2)
            for (int d1 = 2; d1 <= 8; d1 += 2)
                for (int d2 = 1; d2 <= 9; d2 += 2)
                    for (int d3 = 2; d3 <= 6; d3 += 4) // special
                        for (int d5 = 2; d5 <= 8; d5 += 2)
                            for (int d6 = 1; d6 <= 9; d6 += 2)
                                for (int d7 = 2; d7 <= 8; d7 += 2)
                                    for (int d8 = 1; d8 <= 9; d8 += 2) {
                                        if (d0 == d2 || d0 == d6 || d0 == d8 || d2 == d6 || d2 == d8 || d6 == d8)
                                            continue;
                                        if (d1 == d3 || d1 == d5 || d1 == d7 || d3 == d5 || d3 == d7 || d5 == d7)
                                            continue;
                                        digits[0] = d0;
                                        digits[1] = d1;
                                        digits[2] = d2;
                                        digits[3] = d3;
                                        digits[4] = 5;  // special
                                        digits[5] = d5;
                                        digits[6] = d6;
                                        digits[7] = d7;
                                        digits[8] = d8;
                                        digits[9] = 0; // special
//                                        System.out.println(Arrays.toString(digits));
                                        if (isDivisible(digits)) {
                                            System.out.println("found: " +Arrays.toString(digits));
                                        }
                                    }
    }

    private static boolean isDivisible(int[] digits) {
        long n = valueOf(digits);
        return n % 10 == 0
                && (n / 10) % 9 == 0
                && (n / 100) % 8 == 0
                && (n / 1000) % 7 == 0
                && (n / 10000) % 6 == 0
                && (n / 100_000) % 5 == 0
                && (n / 1_000_000) % 4 == 0
                && (n / 10_000_000) % 3 == 0
                && (n / 100_000_000) % 2 == 0
                && (n / 1_000_000_000) % 1 == 0;
    }

    private static long valueOf(int[] digits) {
        long n = digits[0];
        for (int i = 1; i < digits.length; i++) {
            n = (n * 10) + digits[i];
        }
//        System.out.println("n: " + n);
        return n;
    }

    public static void main(String[] args) {
        badFind();
    }
}
