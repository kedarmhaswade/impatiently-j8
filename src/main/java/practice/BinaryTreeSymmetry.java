package practice;

/**
 * <p>
 *     Given a binary tree, determine if it is symmetric. A binary tree is symmetric when you take a mirror image of
 *     it in a vertical line passing through its root.
 * </p>
 * <p>
 *     The mirror is associated with the height of a tree rooted at every node. For the entire tree to be symmetric,
 *     the left and right children of the root should have the same key and those two should be symmetric themselves.
 *     Thus, we need to have 1 tree that is symmetric about the line passing through root, 2 trees that are separately
 *     symmetric about two lines passing through left and right children of the root, 4 trees that are separately
 *     symmetric about the four lines passing through their roots and so on.
 * </p>
 * Created by kmhaswade on 9/7/16.
 */
public class BinaryTreeSymmetry {
    static class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;

        Node(T key) {
            this.key = key;
        }
    }
    static <T> boolean isSymmetric(Node<T> node) {
        return node == null || areChildrenSymmetric(node.left, node.right);
    }

    /**
     * Recursively determines if the tree rooted at the given node is <i>symmetric</i>.
     */
    private static <T> boolean areChildrenSymmetric(Node<T> left, Node<T> right) {
        if (left == null && right == null)
            return true;
        else if (left != null && right != null)
            return (left.key == right.key || left.key.equals(right.key))
                    && areChildrenSymmetric(left.left, left.right)
                    && areChildrenSymmetric(right.left, right.right);
        else
            return false; // one is null and other isn't
    }

}
