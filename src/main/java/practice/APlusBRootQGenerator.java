package practice;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * <p>
 *     Generates in order the first k numbers of the form a + b.sqrt(q), where a and b are non-negative integers
 *     and q is a number that is <i>not</i> a square of an integer (e.g. 2).
 * </p>
 * Created by kmhaswade on 8/26/16.
 */
public class APlusBRootQGenerator {

    public static class Node implements Comparable<Node> {

        final int a, b, q;
        final double value;

        public Node(int a, int b, int q) {
            this.a = a;
            this.b = b;
            this.q = q;
            this.value = a + (b * Math.sqrt(q));
        }

        public int compareTo(Node that) {
            return Double.compare(this.value, that.value);
        }

        public Stream<Node> successors() {
            return Stream.of(new Node((a + 1), b, q), new Node(a, b + 1, q));
        }
    }

    public void get(int k, Consumer<Double> c) {
        SortedSet<Node> set = new TreeSet<>();
        int a = 0, b = 0, q = 2;
        set.add(new Node(a, b, q));
        for (int i = 0; i < k; i++) {
            Node n = set.first();
            set.remove(n);
            c.accept(n.value);
            n.successors().forEach(set::add); // ignore the return value
        }
    }
//    public void print() {
//        int a = 0, b = 0;
//        double s = Math.sqrt(q);
//        for (int i = 0; i < k; i++) {
//            System.out.println(a + (b * s));
//            double ainc = (a + 1) + (b * s);
//            double binc = a + (b + 1) * s;
//            if (ainc < binc) {
//                a += 1;
//            } else {
//                assert ainc >= binc;
//                b += 1;
//            }
//        }
//    }
}
