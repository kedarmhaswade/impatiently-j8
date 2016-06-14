package tmp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 3/22/16.
 */
public class BTPaths {
    private static final class BT<T> {
        final T key;
        BT<T> left;
        BT<T> right;

        BT(T key) {
            this.key = key;
        }
        @Override
        public String toString() {
            return key.toString() + (left == null && right == null ? " leaf" : "");
        }
    }

    public static void main(String[] args) {
        BT<Integer> t = new BT<>(100);
        t.left = new BT<>(50);
        t.right = new BT<>(150);
        t.left.right = new BT<>(75);
        t.left.right.left = new BT<>(63);
        t.right.left = new BT<>(125);
        t.right.right = new BT<>(200);
        preOrderPrintPaths(t, new ArrayDeque<>(10));
    }

    static <T> void preOrderPrintPaths(BT<T> node, Deque<BT<T>> q) {
        if (node == null)
            return;
        if (node.left != null) {
            q.addLast(node);
            preOrderPrintPaths(node.left, q);
        }
        if (node.right != null) {
            q.addLast(node);
            preOrderPrintPaths(node.right, q);
        }
        if (node.left == null && node.right == null) {
            System.out.println(q.stream().map(n -> n.key.toString()).collect(Collectors.joining
                    ("->")) + "->" + node.key);
        }
        if (!q.isEmpty())
            q.removeLast();
    }
}
