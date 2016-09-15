package practice;

import practice.Utils.BinTreeNode;

/**
 * <p>
 *     Given a binary tree with nodes with no parent ref, find the lowest (having maximum depth) common ancestor
 *     of two given nodes in the tree.
 * </p>
 * <p>
 *     With parent node reference, this problem is straightforward. Without parent reference however, it is a bit
 *     trickier. A node n is the LCA of given nodes n1 and n2 if both n1 and n2 lie in the subtree rooted at n. When
 *     we want some entity to be remembered for an entire subtree rooted at a given node n,
 *     we need to
 *     <ol>
 *         <li>traverse the children first,</li>
 *         <li>look at the entity of interest returned children in order,</li>
 *         <li>return the entity as is if the criterion is satisfied or update it based on what the children
 *         return and then return the updated entity</li>
 *     </ol>
 *     1) above suggests the use of the post order traversal. This is the post order traversal adaptation. In the
 *     rudimentary post order traversal all we do is recursively visit the children first in order (left child first,
 *     then right) and then merely visit the current node; remember we return nothing in this process. Adapting this
 *     recursive strategy to actually return something is a useful technique.
 * </p>
 * <p>
 *     The entity of essence in this case appears to be something that stores two things -- how many of the given
 *     (two) nodes (whose LCA is to be found) we actually found in the subtree rooted at the given node and their
 *     LCA itself, if we found it.
 * </p>
 * Created by kmhaswade on 9/7/16.
 */
public class LCA {
    static class Tracker<T> {
        int numNodesFound; // 0, 1 or 2.
        BinTreeNode<T> ancestor; // LCA of the nodes that we want to find an LCA of

        public Tracker(int n, BinTreeNode<T> ancestor) {
            this.numNodesFound = n;
            this.ancestor = ancestor;
        }
        final static Tracker NULL = new Tracker<>(0, null);
    }
    static <T> BinTreeNode<T> find(BinTreeNode<T> node, BinTreeNode<T> n1, BinTreeNode<T> n2) {
        return traverse(node, n1, n2).ancestor;
    }

    /**
     * Recursively traverses the subtree rooted at node and returns the {@linkplain Tracker} that tracks the LCA
     * of gives nodes.
     * @param node root of the subtree
     * @param n1 first node
     * @param n2 second node
     * @param <T>
     * @return the LCA of two nodes rooted at node, null otherwise
     */
    private static <T> Tracker<T> traverse(BinTreeNode<T> node, BinTreeNode<T> n1, BinTreeNode<T> n2) {
        if (node == null)
            return Tracker.NULL;
        Tracker<T> leftTracker = traverse(node.left, n1, n2);
        if (leftTracker.numNodesFound == 2) {
            assert leftTracker.ancestor != null; // programming error
            return leftTracker;
        }
        Tracker<T> rightTracker = traverse(node.right, n1, n2);
        if (rightTracker.numNodesFound == 2) {
            assert rightTracker.ancestor != null; // programming error
            return rightTracker;
        }
        // both the subtrees do not have the given two nodes
        assert leftTracker.ancestor == null && rightTracker.ancestor == null; // we must not have found the lca
        int nFound = leftTracker.numNodesFound + rightTracker.numNodesFound + (n1 == node ? 1 : 0) + (n2 == node ? 1 : 0);
        // if the numNodesFound is 2 now, it is only because node == n1 or node == n2, then node is the lca
        BinTreeNode<T> lca = nFound != 2 ? null : node;
//        System.out.println(nFound + ", " + lca + " at node: " + node.key);
        return new Tracker<>(nFound, lca);
    }
}
