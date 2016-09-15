package practice;

import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static practice.BinaryTreeSymmetry.*;

/**
 * Created by kmhaswade on 9/7/16.
 */
public class BinaryTreeSymmetryTest {

    @Test
    public void testIsSymmetric() throws Exception {
        BinaryTreeSymmetry.Node<String> root = null;
        assertTrue(isSymmetric(root));

        root = new Node<>("a");
        root.left = new Node<>("b");
        root.right = new Node<>("b");
        root.left.left = new Node<>("c");
        root.left.right = new Node<>("c");
        root.right.left = new Node<>("c");
        root.right.right = new Node<>("c");
        assertTrue(isSymmetric(root));

        root = new Node<>("a");
        root.left = new Node<>("b");
        root.right = new Node<>("b");
        root.left.left = new Node<>("c");
        root.left.right = new Node<>("c");
//        root.right.left = new Node<>("c"); // missing
        root.right.right = new Node<>("c");
        assertFalse(isSymmetric(root));

    }
}