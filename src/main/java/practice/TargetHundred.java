package practice;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *     Given the nine decimal digits [1..9], arrange them in order with either the
 *     +, -, or _ symbols before, after or in between any two to make the sum 100.
 *     The _ symbol acts as a concatenation and its value is value of the number
 *     formed by the concatenation of the digits in question. For instance,
 *     the value of 3_4 is 34, the value of 4_5_6 is 456 and so on. Note that the symbol
 *     _ itself does not need to be visible in the string representation of the
 *     arrangement.
 * </p>
 * <p>
 *     As an example, one such arrangement is:
 *     <code> 12 + 3 + 4 + 5 - 6 - 7 + 89 = 100</code>
 * </p>
 * Created by kedar on 11/30/16.
 */
public class TargetHundred {

    /*
    // this is the first attempt that does not work -- keeping here for history
    static void isSum(String s, int d, int value, int target) {
        if (d == 9) { // the base case
            if (value == target) {
                System.out.println(s);
            } else {
                // consumed all digits, but we did not reach the target, do nothing
            }
        } else {
            int nextd = d + 1;
            isSum(s + "-" + nextd, nextd, value - nextd, target);
            isSum(s + "+" + nextd, nextd, value + nextd, target);
            isSum(s + nextd, nextd, value < 0 ? (value * 10) + nextd : (value * 10) - nextd, target);
        }
    }
    */
    static void isSum(String s, int d, int target) {
        if (d == 9) { // the base case
            if (eval(s) == target) {
                System.out.println(s);
            } else {
                // consumed all digits, but we did not reach the target, do nothing
            }
        } else {
            int nextd = d + 1;
            isSum(s + "-" + nextd, nextd, target);
            isSum(s + "+" + nextd, nextd, target);
            isSum(s + nextd, nextd, target);
        }
    }

    static int eval(String s) {
        // s is assumed well-formed
        int answer = 0, slen = s.length();
        Deque<Character> operators = new LinkedList<>();
        Deque<Integer> operands = new LinkedList<>();
        int currentNumber = 0;
        char c = c = s.charAt(0);
        int i = 0;
        if (c == '+' || c == '-') {
            operands.add(0);
            operators.add(c);
            i = 1;
        }
        while (i < slen) {
            c = s.charAt(i);
            if (c == '+' || c == '-') {
                operators.add(c);
                operands.add(currentNumber);
                currentNumber = 0;
            } else {
                int dig = c - '0';
                if (currentNumber == 0)
                    currentNumber = dig;
                else
                    currentNumber = currentNumber * 10 + dig;
            }
            i += 1;
        }
        operands.add(currentNumber);
//        System.out.println(s);
//        System.out.println(operators);
//        System.out.println(operands);
        while (! operators.isEmpty()) {
            c = operators.remove();
            if (c == '+') {
                answer = operands.remove() + operands.remove();
            } else {
                answer = operands.remove() - operands.remove();
            }
            operands.addFirst(answer);
//            System.out.println(operators);
//            System.out.println(operands);
        }
        return operands.remove();

    }
    public static void main(String[] args) {
        isSum("", 0, 100);
//        System.out.println(eval("1-2-3+45"));
    }
}
