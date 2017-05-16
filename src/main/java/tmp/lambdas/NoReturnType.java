package tmp.lambdas;

import java.util.Comparator;

/**
 * <p>
 *     You don't specify the return type of the lambda expressions.
 * </p>
 * Created by kedar on 5/14/17.
 */
public class NoReturnType {
    public static void main(String[] args) {
        Comparator<String> lc = (String first, String second) -> first.length() - second.length();
        // here, the only method is compareTo(String, String) that takes two strings and returns
        // an integer, so this lc can be passed whereever an int is required.
        printSquare(comparesStringLengths(lc));
    }
    static void printSquare(int x) {
        int y = x * x; // overflow may occur
        System.out.println(y);
    }
    static int comparesStringLengths(Comparator<String> c) {
        return c.compare("john", "man");
    }
}
