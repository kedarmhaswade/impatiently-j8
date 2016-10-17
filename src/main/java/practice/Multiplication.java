package practice;

/**
 * Created by kedar on 1/10/16.
 */
public class Multiplication {

    static long gradeSchool(int a, int b) {
        if (a == 0 || b == 0)
            return 0L;
        int sign = (a > 0 && b > 0) || (a < 0 && b < 0) ? 1 : -1;
        a = a < 0 ? -a : a;
        b = b < 0 ? -b : b;
        long sum = 0L;
        int i = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                sum += (a << i);
            }
            i += 1;
            b = (b >>> 1);
        }
        return (sign > 0) ? sum : -sum;
    }
}
