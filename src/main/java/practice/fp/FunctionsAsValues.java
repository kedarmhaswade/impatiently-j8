package practice.fp;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * <p> Demonstrates section 1.7 in Michaelson's Functional Programming Through Lambda Calculus.
 * Clearly, it uses Java instead of Pascal. The idea is to be able to pass and return functions.
 * </p>
 */
public class FunctionsAsValues {

    private static BiFunction<Integer, Integer, Integer> ADD = (x, y) -> x + y;
    private static BiFunction<Integer, Integer, Integer> SUB = (x, y) -> x - y;
    private static BiFunction<Integer, Integer, Integer> MUL = (x, y) -> x * y;
    private static BiFunction<Integer, Integer, Integer> DIV = (x, y) -> x / y; // integer division

    // this method _returns_ a "function"
    static BiFunction<Integer, Integer, Integer> get(int ID) {
        switch (ID) {
            case 0: return ADD;
            case 1: return SUB;
            case 2: return MUL;
            case 3: return DIV;
            default: throw new IllegalArgumentException("found: " + ID + ", must be 0, 1, 2 or 3");
        }
    }
    static int caller(BiFunction<Integer, Integer, Integer> f, int x, int y) {
        return f.apply(x, y);
    }
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> bif = get(new Random().nextInt(4));
        System.out.println("result of calling the function: " + caller(bif, 1, 2));
    }
}
