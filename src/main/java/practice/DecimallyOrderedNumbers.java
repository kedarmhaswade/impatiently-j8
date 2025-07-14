package practice;

import java.util.Arrays;

public class DecimallyOrderedNumbers {
    // TODO: Use Enum instead
    public static final char PLUS = '+';
    public static final char MINUS = '-';
    // prints the numbers that can be formed from decimal digits in order by using +, -, and concatenation

    static void formExpressionsRecursively(int y, String expr) {
        if (y == 9) {
            evalPrint(expr + PLUS + y);
            evalPrint(expr + MINUS + y);
            evalPrint(expr + y);
        } else {
            formExpressionsRecursively(y + 1, (expr + PLUS + y));
            formExpressionsRecursively(y + 1, (expr + MINUS + y));
            formExpressionsRecursively(y + 1, (expr + y));
        }
    }

    private static void evalPrint(String expr) {
        long result = 0L;
        String[] operandStr = expr.split("[+-]");
        long[] numbers = Arrays.stream(operandStr).mapToLong(Long::parseLong).toArray();
        String operStr = expr.replaceAll("[\\d]", ""); // something like "", "+-", "--+"
        if (operStr.length() == 0) {
            System.out.println(expr + "=" + Long.parseLong(expr));
            return;
        }
        assert numbers.length == 1 + operStr.length() : "associativity rule violated, #numbers: " + numbers.length + ", #operations: " + operStr.length();
        int j = 0;
        for (int i = 0; i < operStr.length(); i++) {
            switch (operStr.charAt(i)) {
                case PLUS:
                    if (i == 0) {
                        result = numbers[j] + numbers[j + 1];
                        j += 2;
                    } else {
                        result += numbers[j];
                        j += 1;
                    }
                    break;
                case MINUS:
                    if (i == 0) {
                        result = numbers[j] - numbers[j + 1];
                        j += 2;
                    } else {
                        result -= numbers[j];
                        j += 1;
                    }
                    break;
                default:
                    throw new IllegalStateException("invalid char: " + operStr.charAt(i) + ", at index: " + i);
            }
        }
        if (result >= 1 && result <= 200) {
            System.out.println(expr + " = " + result);
        }
    }

    public static void main(String[] args) {
        formExpressionsRecursively(2, "1");
    }
}
