package practice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecimallyOrderedNumbers2 {
    public static final char PLUS = '+';
    public static final char MINUS = '-';
    private static Map<Long, List<String>> map = new HashMap<>(1 << 14);

    private static void eval(int dig, String expr, long result) {
        if (dig >= 9) {
//            System.out.println(expr + " = " + result);
            map.putIfAbsent(result, new ArrayList<>(1 << 4));
            map.get(result).add(expr);
        } else {
            dig += 1;
            eval(dig, expr + PLUS + dig, result + dig);
            eval(dig, expr + MINUS + dig, result - dig);
            long last = extractSignedLast(expr); // not a constant time operation, but let me bite the bullet!
            eval(dig, expr + dig, result + last * 9 + (last > 0 ? dig : -dig));
        }
    }

    private static long extractSignedLast(String expr) {
        int k = expr.length() - 1;
        while (k >= 0 && Character.isDigit(expr.charAt(k))) {
            k--;
        }
        if (k >= 0 && (expr.charAt(k) == PLUS || expr.charAt(k) == MINUS)) {
            k--;
        }
        return Long.parseLong(expr.substring(k + 1));
    }

    public static void main(String[] args) {
        eval(1, "1", 1);
        System.out.println("Number of numbers that have this representations: " + map.size());
        map.keySet().stream().sorted().forEach(e -> System.out.println(e + " (" + map.get(e).size() + ") = " + map.get(e)));
    }
}
