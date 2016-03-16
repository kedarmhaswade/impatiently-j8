package tmp;

/** Mohit asked the following:
 * <p>
 *     You are given a coin with a number N on it. You can either
 *     <ul>
 *         <li>encash this coin immediately whereupon your yield is N dollars, or</li>
 *         <li>convert this coin into three coins with numbers N/4, N/3 and N/2 (this is integer division).
 *         You can continue doing this for returned coins.</li>
 *     </ul>
 *     What is the maximum yield when you have finally encashed all your coins?
 * </p>
 * Created by kmhaswade on 3/16/16.
 */
public class MaxGain {
    static long nc = 0;
    static long y(int n) {
        nc += 1;
        if (n < 12)
            return  n;
        return Math.max(n, y(n/4) + y(n/3) + y(n/2));
    }

    public static void main(String[] args) {
        System.out.println(y(12) + ", nc: " + nc);
        nc = 0;
        System.out.println(y(20) + ", nc: " + nc);
        nc = 0;
        System.out.println(y(24) + ", nc: " + nc);
        nc = 0;
        System.out.println(y(36) + ", nc: " + nc);
        nc = 0;
        System.out.println(y(100_000_000) + ", nc: " + nc);
    }
}
