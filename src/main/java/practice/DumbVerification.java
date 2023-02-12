package practice;

import java.io.IOException;

/**
 * Ali asks in Beyond Euclid # 60:
 * <p>
 * x is a five-digit number, and let y and z be the quotient and the remainder, respectively, when x is divided by 100.
 * For how many values of x is y + z divisible by 11?
 * </p>
 * <p>
 * I have proved it to be 10. Using this program to do a dumb verification ;-).
 * </p>
 */
public class DumbVerification {
    public static void main(String[] args) throws IOException {
        int low = 10_000;
        int high = 99_999;
        int c = 0;
        for (int x = low; x <= high; x++) {
            int y = x / 100;
            int z = x % 100;
            if ((y + z) % 11 == 0) {
                c += 1;
                System.out.println("x: " + x);
//                System.in.read();
            }
        }
        System.out.println("count:" + c);
    }
}
