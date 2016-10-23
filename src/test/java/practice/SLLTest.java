package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static practice.SLL.*;

/**
 * Created by kmhaswade on 7/31/16.
 */
public class SLLTest {

    @Test
    public void toStringTest() {
        ListNode<String> head = new ListNode<>("a");
        assertEquals("a", SLL.toString(head));
        assertEquals("null", SLL.toString(null));
        head.add("b");
        assertEquals("a->b", SLL.toString(head));
        head.add("b").add("c");
        assertEquals("a->b->c", SLL.toString(head));
        head.add("b").add("c").add("d");
        assertEquals("a->b->c->d", SLL.toString(head));
    }
    @Test
    public void testReverseNull() {
        assertNull(reverse(null));
    }
    @Test
    public void testReverseSingleton() {
        ListNode<String> head = new ListNode<>("A");
        assertEquals(head, reverse(head));
    }
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
    @Test
    public void testRecReverse5() throws Exception {
        ListNode<Integer> five = new ListNode<>(5);
        ListNode<Integer> four = new ListNode<>(4, five);
        ListNode<Integer> three = new ListNode<>(3, four);
        ListNode<Integer> two = new ListNode<>(2, three);
        ListNode<Integer> one = new ListNode<>(1, two);
        ListNode<Integer> r = recReverse(one);
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
    @Test
    public void testReverseAcc() {
        ListNode<Integer> five = new ListNode<>(5);
        ListNode<Integer> four = new ListNode<>(4, five);
        ListNode<Integer> three = new ListNode<>(3, four);
        ListNode<Integer> two = new ListNode<>(2, three);
        ListNode<Integer> one = new ListNode<>(1, two);
        ListNode<Integer> r = reverseAcc(one);
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
    @Test
    public void testReverseAccNoTailCall() {
        ListNode<Integer> five = new ListNode<>(5);
        ListNode<Integer> four = new ListNode<>(4, five);
        ListNode<Integer> three = new ListNode<>(3, four);
        ListNode<Integer> two = new ListNode<>(2, three);
        ListNode<Integer> one = new ListNode<>(1, two);
        ListNode<Integer> r = reverseAccNoTailCall(one);
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

    @Test
    public void testAddMsdFirstEqualNumberOfDigits() {
        ListNode<Integer> nine = new ListNode<>(9);
        ListNode<Integer> eight = new ListNode<>(8);
        ListNode<Integer> sum = addMsdFirst(nine, eight);
        assertEquals(1L, (long) sum.key);
        assertEquals(7L, (long) sum.next.key);
        assertNull(sum.next.next);
    }
    @Test
    public void testAddMsdFirst() {
//        ListNode<Integer> oneThreeFourNine = new ListNode<>(9);
//        oneThreeFourNine = new ListNode<>(4, oneThreeFourNine);
//        oneThreeFourNine = new ListNode<>(3, oneThreeFourNine);
//        oneThreeFourNine = new ListNode<>(1, oneThreeFourNine);
//        ListNode<Integer> threeNineEight = new ListNode<>(8);
//        threeNineEight = new ListNode<>(9, threeNineEight);
//        threeNineEight = new ListNode<>(3, threeNineEight);
//        ListNode<Integer> sum = addMsdFirst(oneThreeFourNine, threeNineEight);
//        while (sum != null) {
//            System.out.println(sum.key);
//            sum = sum.next;
//        }
//        assertEquals(1L, (long) sum.key);
//        assertEquals(8L, (long) sum.next.key);
//        assertEquals(3L, (long) sum.next.next.key);
//        assertEquals(7L, (long) sum.next.next.key);
    }
}