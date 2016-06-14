package practice;

/** <p> It is possible to <i>iteratively </i>traverse a {@code Binary Tree} by using a comparison of pointers or
 * references. </p>
 * This class demonstrates such a traversal. This idea is from Pat Morin's Open Data Structures.
 * Created by kmhaswade on 6/6/16.
 */
public class PointerComparisonTraversal<T> {

    private static class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T key) {
            this.key = key;
        }

        public Node<T> setLeft(Node<T> left) {
            this.left = left;
            left.parent = this;
            return left;
        }
        public Node<T> setRight(Node<T> right) {
            this.right = right;
            right.parent = this;
            return right;
        }
        @Override
        public String toString() {
            String l, r, p;
            l = left == null ? "null" : left.key.toString();
            r = right == null ? "null" : right.key.toString();
            p = parent == null ? "null" : parent.key.toString();
//            return "[key: " + key + ", left: " + l + ", right: " + r + ", parent: " + p + "]";
            return "[key: " + key + "]";
        }
        public static <T> Node<T> createBasicIntegerTree(T... values) {
            Node<T> root = new Node<>(values[0]);
            Node<T> left = new Node<>(values[1]);
            root.setLeft(left);
            Node<T> right = new Node<>(values[2]);
            root.setRight(right);
            return root;
        }
        public static <T> Node<T> tree1(T value) {
            Node<T> root = new Node<>(value);
            return root;
        }
        public static <T> Node<T> tree5(T... values) {
            Node<T> root = new Node<>(values[0]);
            Node<T> left = new Node<>(values[1]);
            root.setLeft(left);
            Node<T> right = new Node<>(values[2]);
            root.setRight(right);
            Node<T> t = new Node<>(values[3]);
            left.setRight(t);
            t = new Node<>(values[4]);
            right.setLeft(t);
            return root;
        }
        public static <T> Node<T> justRight(T v1, T v2) {
            Node<T> root = new Node<>(v1);
            Node<T> right = new Node<>(v2);
            root.setRight(right);
            return root;
        }
    }
    public void traverse(Node<T> node) {
        if (node == null)
            return;
        Node<T> curr = node, prev = node.parent, next = node.left;
        while (true) {
            if (prev == curr.parent) {
                next = curr.left;
                if (next == null)
                    next = curr.right;
                if (next == null)
                    next = curr.parent;
            } else if (prev == curr.left) {
                next = curr.right;
                if (next == null)
                    next = curr.parent;
            } else if (prev == curr.right) {
                next = curr.parent;
            } else {
                System.out.println("interesting case, is that a bug? : " + curr);
            }
            if (next == curr.parent)
                System.out.println("visited: " + curr);
            if (prev == node.right && next == node.parent)
                break;
//            System.out.println("curr: " + curr + ", prev: " + prev + ", next: " + next);
            prev = curr;
            curr = next;
        }
    }

    public static void main(String[] args) {
        PointerComparisonTraversal t = new PointerComparisonTraversal();
        System.out.println("==============================================");
        Node<String> r1 = Node.tree1("foo");
        t.traverse(r1);
        System.out.println("==============================================");
        Node<Integer> r2 = Node.tree5(1, 2, 3, 4, 5);
        t.traverse(r2);
        System.out.println("==============================================");
        Node<Integer> r3 = Node.justRight(1, 2);
        t.traverse(r3);
        System.out.println("==============================================");
    }
}
