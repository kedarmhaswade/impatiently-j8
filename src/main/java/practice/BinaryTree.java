package practice;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 *     Various routines/problems for binary trees.
 * </p>
 * Created by kmhaswade on 8/1/16.
 */
public class BinaryTree {


    public static final class TreeNode<T> {
        final T key;
        TreeNode<T> parent;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode(T key) {
            this.key = key;
        }
        TreeNode(T key, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
        // additional
        boolean locked; // is this node locked?
        int numberOfDescendantsLocked; // number of locked descendants

        public boolean lock() {
            if (this.locked) { // already locked, unlock first
                return false;
            } else {
                if (this.numberOfDescendantsLocked != 0) {
                    return false; // at least one descendant is locked, so this node can't be locked
                }
                // this node is not locked, none of the descendants is locked, so this can be locked
                this.locked = true;
                TreeNode<T> tmp = this.parent;
                while (tmp != null) {
                    tmp.numberOfDescendantsLocked += 1;
                    tmp = tmp.parent;
                }
                return true;
            }
        }
        public boolean unlock() {
            if (this.locked) {
                this.locked = false;
                TreeNode<T> tmp = this.parent;
                while (tmp != null) {
                    tmp.numberOfDescendantsLocked -= 1;
                    tmp = tmp.parent;
                }
                return true;
            } else { // already unlocked, lock first
                return false;
            }
        }
    }

    /**
     * A rudimentary binary tree creation method with a caveat. Provide null for the missing child.
     * @param ts the values of keys
     * @param <T> the type of keys
     * @return root node of the binary tree created thus
     */
    static <T> TreeNode<T> createLevel(T... ts) {
        if (ts.length == 0)
            return null;
        else {
            List<TreeNode<T>> list = Arrays.stream(ts)
                    .map(v -> v == null ? null : new TreeNode<>(v))
                    .collect(toList());
            TreeNode<T> root = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                int parentIdx = (i - 1) / 2;
                TreeNode<T> p = list.get(parentIdx);
                TreeNode<T> child = list.get(i);
                if (p != null) {
                    if (i % 2 == 1) {
                        p.left = child;
                    } else {
                        p.right = child;
                    }
                }
            }
            return root;
        }
    }
}
