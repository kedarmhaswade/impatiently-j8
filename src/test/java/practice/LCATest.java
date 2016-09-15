package practice;

import org.junit.Test;
import practice.Utils.BinTreeNode;

import static org.junit.Assert.assertSame;
import static practice.LCA.find;

/**
 * Created by kmhaswade on 9/7/16.
 */
public class LCATest {

    @Test
    public void testFind() throws Exception {
        BinTreeNode<String> root = new BinTreeNode<>("root");
        BinTreeNode<String> l = new BinTreeNode<>("l");
        BinTreeNode<String> r = new BinTreeNode<>("r");
        root.left = l;
        root.right = r;
        BinTreeNode<String> ll = new BinTreeNode<>("ll");
        BinTreeNode<String> lr = new BinTreeNode<>("lr");
        l.left = ll;
        l.right = lr;
        BinTreeNode<String> rl = new BinTreeNode<>("rl");
        BinTreeNode<String> rr = new BinTreeNode<>("rr");
        r.left = rl;
        r.right = rr;
        assertSame(root, find(root, ll, rr));
        assertSame(l, find(root, l, ll));
        assertSame(r, find(root, rl, rr));
    }
}