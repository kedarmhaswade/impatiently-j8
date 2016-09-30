package practice;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *     Implements various calculating machines :-).
 * </p>
 * Created by kmhaswade on 9/21/16.
 */
public class Calculator {

    public static int polish(String expr) {
        if (expr == null || expr.isEmpty())
            return 0; // ?
        Deque<String> stack = new LinkedList<>();
        String[] tokens = expr.split("\\s?,\\s?");
        int len = tokens.length;
        int i = 0;
        Integer result = null;
        String token;
        while (isOperator(token = tokens[i])) {
            stack.push(token);
            i += 1;
        }
        // ith token is a number
        while (i < len) {
            if (result == null) {
                result = Integer.parseInt(tokens[i]);
                i += 1;
                if (i == len)
                    return result;
                result = operate(stack.pop(), result, Integer.parseInt(tokens[i]));
            } else {
                result = operate(stack.pop(), result, Integer.parseInt(tokens[i]));
            }
            i += 1;
        }
        return result;
    }

    private static Integer operate(String op, Integer result, int i) {
        if ("+".equals(op))
            return result + i;
        if ("-".equals(op))
            return result - i;
        if ("*".equals(op))
            return result * i;
        if ("/".equals(op))
            return result / i;
        throw new IllegalStateException("can't reach here with op: " + op + ", result: " + result + ", i: " + i);
    }

    private static boolean isOperator(String s) {
        return s.trim().matches("\\+|\\*|/|-");
    }
}
