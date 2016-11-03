package practice;

/**
 * <p>
 *     Imagine a binary tree such that each of its node's keys is a binary digit and the path from root to a leaf
 *     represents a binary-encoded number with root key being the MSB. Find the sum of all such binary numbers
 *     in a subtree rooted at a given node.
 * </p>
 * Created by kmhaswade on 9/5/16.
 */
public class BinaryNumberBinaryTree {
    public static final class Node {
        final int key; // 0 <= key <= 1;
        Node left;
        Node right;
        private Node(int key) {
            this.key = key;
        }
        static Node zero() {
            return new Node(0);
        }
        static Node one() {
            return new Node(1);
        }
    }
    static int sum(Node node, int soFar) {
        if (node == null)
            return 0;
        int soFar1 = (soFar << 1) + node.key;
        if (node.left == null && node.right == null) {
            // leaf
            return soFar1;
        }
//        System.out.println("soFar: " + soFar + ", soFar1: " + soFar1);
        return sum(node.left, soFar1) + sum(node.right, soFar1);
    }
//    static final class Status {
//        int leftDepth;
//        int rightDepth;
//        int sum;
//        Status (int leftDepth, int rightDepth, int sum) {
//            this.leftDepth = leftDepth;
//            this.rightDepth = rightDepth;
//            this.sum = sum;
//        }
//        static final Status NULL = new Status(-1, -1, 0);
//    }
//    static int sum(Node tree) {
//        return getStatus(tree).sum;
//    }
//
//    /**
//     * Recursively computes the {@linkplain Status}.
//     * @param tree given tree
//     * @return Status object
//     */
//    private static Status getStatus(Node tree) {
//        if (tree == null)
//            return Status.NULL;
//        Status leftStatus = getStatus(tree.left);
//        Status rightStatus = getStatus(tree.right);
//        int ld = Math.max(leftStatus.leftDepth, leftStatus.rightDepth) + 1;
//        int rd = Math.max(rightStatus.leftDepth, rightStatus.rightDepth) + 1;
//        int ls = leftStatus.sum;
//        int rs = rightStatus.sum;
//        int sum = ls + rs;
//        return new Status(ld, rd, tree.key == 1 ? sum + (1 << ld) + (1 << rd) : sum);
//    }
}
