package practice;

import org.junit.Test;
import practice.BinaryNumberBinaryTree.Node;

import static org.junit.Assert.assertEquals;
import static practice.BinaryNumberBinaryTree.Node.one;
import static practice.BinaryNumberBinaryTree.Node.zero;
import static practice.BinaryNumberBinaryTree.sum;

/**
 * Created by kmhaswade on 9/5/16.
 */
public class BinaryNumberBinaryTreeTest {

    @Test
    public void testSum1() {
        /*
                1
            1       1
          0   1   1   -

          110 + 111 + 111 = 20
         */
        Node root = one();
        root.left = one();
        root.right = one();
        root.left.left = zero();
        root.left.right = one();
        root.right.left = one();
        assertEquals(20L, sum(root, 0));
    }
    @Test
    public void testSum2() throws Exception {
        /*
                  1
            0           1
         1    1     1      0
       -  1  1 -   0 0    1  1

       There are 6 leaves, the corresponding numbers are:
       1011 = 11, 1011 = 11, 1110 = 14, 1110 = 14, 1101 = 13, 1101 = 13
       sum = 76
         */
        Node root = one();
        root.left = zero();
        root.right = one();
        root.left.left = one();
        root.left.right = one();
        root.right.left = one();
        root.right.right = zero();
        root.left.left.right = one();
        // leaves
        root.left.right.left = one();
        root.right.left.left = zero();
        root.right.left.right = zero();
        root.right.right.left = one();
        root.right.right.right = one();
        assertEquals(76L, sum(root, 0));
    }
}