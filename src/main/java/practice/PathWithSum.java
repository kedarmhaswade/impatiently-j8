package practice;

import practice.Utils.BinTreeNode;

/**
 * <p>
 *     Given a binary tree of numbers, find if a path from root to leaf sums up to a given number.
 * </p>
 * <p>
 *     The technique is similar to the postorder adaptation that {@linkplain LCA} demonstrates. Here, however, we are
 *     passing some contextual information to the children and when a child is leaf, we return something meaningful.
 *     So, preorder is more suitable.
 * </p>
 * Created by kmhaswade on 9/7/16.
 */
public class PathWithSum {

    static boolean find(BinTreeNode<Integer> tree, int sum) {
        return traverse(tree, sum, 0);
    }

    private static boolean traverse(BinTreeNode<Integer> tree, int sum, int partial) {
        if (tree == null)
            return false;
        partial += tree.key;
        if (tree.left == null && tree.right == null) { // Leaf
            return partial == sum;
        }
        // look for it either in left or right tree.
        return traverse(tree.left, sum, partial) || traverse(tree.right, sum, partial);
    }
}
