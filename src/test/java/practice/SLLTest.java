package practice;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static practice.SLL.ListNode;
import static practice.SLL.reverse;

/**
 * Created by kmhaswade on 7/31/16.
 */
public class SLLTest {

    @Test
    public void testReverse2() throws Exception {
        ListNode<Integer> one = new ListNode<>(1);
        ListNode<Integer> two = new ListNode<>(2);
        one.next = two;
        ListNode<Integer> r = reverse(one);
        assertEquals(2, (long) r.key);
        assertEquals(1, (long) r.next.key);
        assertSame(r.next, one);
    }
    @Test
    public void testReverse5() throws Exception {
        ListNode<Integer> five = new ListNode<>(5);
        ListNode<Integer> four = new ListNode<>(4, five);
        ListNode<Integer> three = new ListNode<>(3, four);
        ListNode<Integer> two = new ListNode<>(2, three);
        ListNode<Integer> one = new ListNode<>(1, two);
        ListNode<Integer> r = reverse(one);
        assertEquals(5, (long) r.key);
        assertEquals(4, (long) r.next.key);
        assertEquals(3, (long) r.next.next.key);
        assertEquals(2, (long) r.next.next.next.key);
        assertEquals(1, (long) r.next.next.next.next.key);
        assertSame(r.next, four);
        assertSame(r.next.next, three);
        assertSame(r.next.next.next, two);
        assertSame(r.next.next.next.next, one);
        assertNull(one.next);
    }

}