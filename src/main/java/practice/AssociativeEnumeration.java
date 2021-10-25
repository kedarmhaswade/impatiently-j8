package practice;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;

/**
 * <p>
 * This problem is inspired by Gelfand-Chen's <i>Algebra</i> (a brilliant book).
 * </p>
 * <p>
 * Any associative binary operation like multiplication can be carried out by recursively choosing any two operands in a given
 * sequence, carrying out the operation.
 * </p>
 * <p>
 * Consider that we want to parenthesize the multiplication of a sequence of more than two numbers to show how
 * the multiplication is done. With two numbers a and b we have only one way: <code>a·b</code>. With three numbers a, b, and c, we
 * have two ways: <code>(a·b)·c</code> and <code>a·(b·c)</code>. With four numbers a, b, c, and d, we have 5 ways:
 * <code>((a·b)·c).d, (a·(b·c))·d, (a·b)·(c·d), a·((b·c)·d), and a·(b·(c·d))</code>
 * </p>
 * <p>
 * Given a sequence of multiplicands, print <i>all</i> the ways to parenthesize the expressions that show how the
 * associative operation is carried out.
 * </p>
 */
public class AssociativeEnumeration {

    private static final String MD = "·";
    private static final String LP = "(";
    private static final String RP = ")";
//    public static void print(char[] e, int i, int j) {
//        if (i == j - 2) {
//            System.out.print(e[i] + "·" + e[j-1]);
//        } else {
//            System.out.print("("); print(e, i, j-1); System.out.println(")·" + e[j-1]);
//            System.out.print(e[i]+"·("); print(e, i+1, j); System.out.println(")");
////            if (j-i % 2 == 0) {
////                int m = (i + j) >>> 1;
////                System.out.print("("); print(e, i, m);System.out.println(")·");System.out.print("("); print(e, m, j);System.out.println(")·");
////            }
//        }
//    }


    public static List<String> wrongAlgorithm(char[] e) {
        return wrongAlgorithm(e, 0, e.length);
    }

    private static List<String> wrongAlgorithm(char[] e, int i, int j) {
        if (i == j - 2) {
            return of(e[i] + MD + e[j - 1]);
        } else {
            List<String> curr = new ArrayList<>();
            List<String> prev = wrongAlgorithm(e, i + 1, j);
            for (String p : prev) {
                String c = e[i] + MD + LP + p + RP;
                curr.add(c);
            }
            prev = wrongAlgorithm(e, i, j - 1);
            for (String p : prev) {
                String c = LP + p + RP + MD + e[j - 1];
                curr.add(c);
            }
            if ((j - i) % 2 == 0) {
                int m = i + j >>> 1;
                List<String> left = wrongAlgorithm(e, i, m);
                List<String> right = wrongAlgorithm(e, m, j);
                for (String ls : left) {
                    for (String rs : right) {
                        String c = LP + ls + RP + MD + LP + rs + RP;
                        curr.add(c);
                    }
                }
            }
            return curr;
        }
    }

    public static List<String> permutations(char[] e) {
        return permutations(e, 0, e.length);
    }

    private static List<String> permutations(char[] e, int i, int j) {
        if (i == j - 1) {
            return of(Character.toString(e[i])); // return the character as a string, e.g. "a"
        } else {
            List<String> curr = new ArrayList<>();
            for (int k = i + 1; k < j; k++) {
                List<String> left = permutations(e, i, k);
                List<String> right = permutations(e, k, j);
                for (String ls : left) {
                    for (String rs : right) {
                        String l = ls.length() == 1 ? ls : LP + ls + RP;
                        String r = rs.length() == 1 ? rs : LP + rs + RP;
                        curr.add(l + MD + r);
                    }
                }
            }
            return curr;
        }
    }

    public static void main(String[] args) {
        List<String> p = null;
        p = permutations(new char[]{'a', 'b'});
        System.out.println(p.size() + ": " + p);
        p = permutations(new char[]{'a', 'b', 'c'});
        System.out.println(p.size() + ": " + p);
        p = permutations(new char[]{'a', 'b', 'c', 'd'});
        System.out.println(p.size() + ": " + p);
        p = permutations(new char[]{'a', 'b', 'c', 'd', 'e'});
        System.out.println(p.size() + ": " + p);
        p = permutations(new char[]{'a', 'b', 'c', 'd', 'e', 'f'});
        System.out.println(p.size() + ": " + p);
        p = permutations(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'});
        System.out.println(p.size() + ": " + p);
    }
}
