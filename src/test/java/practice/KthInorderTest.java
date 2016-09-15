package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.KthInorder.Node;
import static practice.KthInorder.get;

/**
 * Created by kmhaswade on 9/5/16.
 */
public class KthInorderTest {

    @Test
    public void testGet() throws Exception {
        Node<String> root = new Node<>("a");
        root.left = new Node<>("b");
        root.right = new Node<>("h");
        root.left.left = new Node<>("c");
        root.left.right = new Node<>("d");
        root.left.left.right = new Node<>("f");
        root.left.right.left = new Node<>("e");
        root.left.right.right = new Node<>("g");
        root.right.left = new Node<>("i");
        root.right.left.right = new Node<>("j");
        root.right.right = new Node<>("k");
        Node<String> kth = new Node<>(null);
        get(root, 4, kth); // e
        System.out.println(kth.key);
        assertEquals("e", kth.key);
    }
}