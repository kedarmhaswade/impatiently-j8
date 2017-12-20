package practice;

/**
 * <p>
 *     Fun with McCarthy's 91 function!
 * </p>
 */
public class Huh {

    private static int calls = 0;
    private static int m91r(int n) {
        calls += 1;
        if (n > 100) return n - 10;
        return m91r(m91r(n + 11));
    }

    private static int m91i(int n) {
        while (n <= 100) {
//            n = m91i(n + 11);
            n += 11;
            while (n <= 100) {
                n += 11;
            }
            n -= 10;
        }
        return n - 10;
    }
    public static void main(String[] args) {
        System.out.println(m91r(50));
        System.out.println(m91i(50));
//        System.out.println("calls: " + calls);
    }
}
