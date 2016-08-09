package practice;

/**
 * <p>
 *     Practice for the singly linked list.
 * </p>
 * Created by kmhaswade on 7/31/16.
 */
public class SLL {
    static final class ListNode<T> {
        final T key;
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
}
