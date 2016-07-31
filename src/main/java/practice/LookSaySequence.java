package practice;

/**
 * <p>
 *     Find the nth term of the look-and-say sequence. The first term of the look-and-say sequence  is 1. Then
 *     you read the previous term by counting the streaks of a number and writing them down as such. The first few
 *     terms of the sequence are: &lt;1, 11, 21, 1211, 111221, 312211, 13112211, 1113212221>. Return the nth
 *     term as a String.
 * </p>
 * Created by kmhaswade on 7/31/16.
 */
public class LookSaySequence {

    static String generate(int n) {
        // bound checks, n should be >= 1
        StringBuilder sb1 = new StringBuilder(1);
        sb1.append("1");
        StringBuilder sb2 = sb1;
//        System.out.println(sb2);
        for (int i = 2; i <= n; i++) {
            sb2 = new StringBuilder(i);
            int cnt = 1;
            char last = sb1.charAt(0);
            for (int j = 1; j < sb1.length(); j++) {
                char curr = sb1.charAt(j);
                if (curr == last) {
                    cnt += 1;
                } else {
                    sb2.append(cnt).append(last);
                    cnt = 1;
                    last = curr;
                }
            }
            sb2.append(cnt).append(last);
//            System.out.println(sb2);
            sb1 = sb2;
        }
        return sb2.toString();
    }
}
