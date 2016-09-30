package practice;

/**
 * <p>
 *     Practice for the singly linked list.
 * </p>
 * Created by kmhaswade on 7/31/16.
 */
public class SLL {
    static final ListNode<Integer> ZERO = new ListNode<>(0);
    static final class ListNode<T> {
        T key; // note: not final (I know, mutability is bad)
        ListNode<T> next;
        public ListNode(T key) {
            this.key = key;
        }
        public ListNode(T key, ListNode<T> next) {
            this.key = key;
            this.next = next;
        }
    }
    static <T> ListNode<T> reverse(ListNode<T> head) {
        ListNode<T> curr = head;
        ListNode<T> prev = null;
        while (curr != null && curr.next != null) {
            ListNode<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr.next = prev;
        return curr;
    }
    static void addMsdFirst(ListNode<Integer> l1, ListNode<Integer> l2, ListNode<Integer> acc) {
        System.out.println("l1.key: " + l1.key + ", l2.key: " + l2.key);
        if (l1.next == null && l2.next == null) {
            acc.next = new ListNode<>(l1.key + l2.key);
            // the only base case
        }
        else if (l1.next == null) { // reached the end of l1, but not l2
            addMsdFirst(l1, l2.next, acc);
        } else if (l2.next == null) {
            addMsdFirst(l1.next, l2, acc);
        } else {
            addMsdFirst(l1.next, l2.next, acc);
            ListNode<Integer> right = acc.next;
            int d = acc.next.key, c = d / 10, r = d % 10;
            right.key = r;
            acc.next = new ListNode<>(l1.key + l2.key + c, acc.next);
        }
    }
    static ListNode<Integer> addMsdFirst(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> acc = ZERO;
        addMsdFirst(l1, l2, acc);
        Integer key = acc.next.key;
        if (key >= 10) {
            acc.next.key = key % 10;
            acc.next = new ListNode<>(key / 10, acc.next);
        }
        return acc.next;
    }
}
