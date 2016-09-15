package practice;

/**
 * <p>
 *     Find the kth node in an inorder traversal of a binary tree.
 * </p>
 * Created by kmhaswade on 9/5/16.
 */
public class KthInorder {
     static final class Node<T> {
         T key;
         Node<T> left;
         Node<T> right;
         Node(T key) {
            this.key = key;
        }
    }

    private static int k = 0;  // static, cheating
    /**
     * Implements a naive solution first. This, obviously is an O(n) algorithm and as such, it's wasteful.
     * There is no way to drop all stack frames and just bottom out the recursion one we find the 'kth' element!
     */
    static <T> void get(Node<T> n, int kexp, Node<T> kth) {
        if (n != null) {
            get(n.left, kexp, kth);
            if (kth.key == null && ++k == kexp) {
                System.out.println("visited " + n.key);
                kth.key = n.key;
            }
            if (kth.key == null)
                get(n.right, kexp, kth);
        }
    }
//    static <T> Node<T> inordeGet(Node<T> n) {
//
//    }
}
