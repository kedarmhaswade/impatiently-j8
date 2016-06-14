package practice;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/** Tests whether the given binary tree is balanced.
 * Created by kmhaswade on 3/18/16.
 */
public class IsBalanced {
    static final class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;
        Node(T key) {
            this.key = key;
        }

    }
    static final class BinaryTreeBuilder<E> {
        static <E> Node<E> build(List<E> preorder, List<E> inorder) {
            return build(preorder, inorder, 0, preorder.size() - 1, 0, inorder.size() - 1);
        }
        /**
         * A unique binary tree can be constructed if you have its preorder and inorder
         * traversals. All elements are required to be unique for this construction.
         * @param preorder List of elements in preorder traversal
         * @param inorder List of elements in inorder traversal
         * @param <E>
         * @return the Root node representing this tree
         */
        static <E> Node<E> build(List<E> preorder, List<E> inorder, int p1, int p2, int i1, int i2) {
            if (p1 == p2) { // base case
                assert i1 == i2 : "p1 = p2 (" + p1 + "), but i1 (" + i1 + ") != i2 (" + i2 + ")";
                assert preorder.get(p1) == inorder.get(i1);
                return new Node<>(preorder.get(p1));
            } else {
                E rootKey = preorder.get(p1);
                int rootIndexInInorder = find(rootKey, inorder);
                int nElemLeftSubtree = rootIndexInInorder - i1;
                int nElemRightSubtree = i2 - rootIndexInInorder;
                Node<E> left = build(preorder, inorder, p1 + 1, p1 + nElemLeftSubtree, i1, rootIndexInInorder - 1);
                Node<E> right = build(preorder, inorder, p1 + nElemLeftSubtree + 1, p2, rootIndexInInorder + 1, i2);
                Node<E> root = new Node<>(rootKey);
                root.left = left;
                root.right = right;
                return root;
            }
        }
        private static <E> int find(E rootKey, List<E> inorder) {
            for (int i = 0; i < inorder.size(); i++) {
                if (inorder.get(i).equals(rootKey))
                    return i;
            }
            throw new NoSuchElementException("Element: " + rootKey + " not found");
        }
    }
    static <E> int maxDepth(Node<E> node) {
        if (node == null)
            return 0;
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }
    static <E> int minDepth(Node<E> node) {
        if (node == null)
            return 0;
        return 1 + Math.min(minDepth(node.left), minDepth(node.right));
    }
    static <E> boolean isBalanced(Node<E> node) {
        if (node == null)
            return true;
        return maxDepth(node) - minDepth(node) <= 1;
    }
    static <E> int size(Node<E> node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }
    static <E> boolean isSizeBalanced(Node<E> node) {
        if (node == null)
            return true;
        return Math.abs(size(node.left) - size(node.right)) <= 1;
    }

    public static void main(String[] args) {
        List<Integer> preorder = Arrays.asList(4, 8, 5, 3, 2, 1);
        List<Integer> inorder = Arrays.asList(5, 8, 3, 2, 4, 1);
        Node<Integer> root = BinaryTreeBuilder.build(preorder, inorder);
        System.out.println(root.key + ", left: " + root.left.key + ", right: " + root.right.key);
    }
}
