package tmp;

/** Solution to: http://stackoverflow.com/questions/35737102/difference-between-two-recursive-methods
 * Created by kmhaswade on 3/1/16.
 */
public class ReflectBinaryTree {

    static class TreeNode<T> {
        TreeNode<T> left;
        TreeNode<T> right;
        T key;
        TreeNode(T key) {
            this.key = key;
        }
    }
    static <T> TreeNode<T>  reflect(TreeNode<T> node) {
        TreeNode<T> left = node.left;
        node.left = node.right;
        node.right = left;
        if (node.left != null)
            reflect(node.left);
        if (node.right != null)
            reflect(node.right);
        return node;
    }
}
