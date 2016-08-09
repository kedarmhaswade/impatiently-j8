package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static practice.BinaryTree.TreeNode;
import static practice.BinaryTree.createLevel;
/**
 * Created by kmhaswade on 8/2/16.
 */
public class BinaryTreeTest {

    @Test
    public void testCreateLevel() throws Exception {
        TreeNode<Integer> root = createLevel(1, null, 2, null, null, 3, null);
        assertEquals(2, (long) root.right.key);
        assertEquals(3, (long) root.right.left.key);
        assertNull(root.left);
    }
}