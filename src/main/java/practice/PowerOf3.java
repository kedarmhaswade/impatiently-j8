package practice;

public class PowerOf3 {

    static boolean isPowerOf3(int x) {
        while (x >= 3) {
            if (x % 3 != 0) {
                return false;
            }
            x /= 3;
        }
        return x == 1;
    }
    // or you can just do: return (x > 0) && (1162261467 % x == 0)
}
