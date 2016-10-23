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
        ListNode<T> add(T key) {
            final ListNode<T> curr = new ListNode<>(key);
            this.next = curr;
            return curr;
        }
    }
    public static <T> String toString(ListNode<T> n) {
        if (n == null)
            return "null";
        StringBuilder b = new StringBuilder();
        ListNode<T> tmp = n;
        while (tmp.next != null) {
            b.append(tmp.key).append("->");
            tmp = tmp.next;
        }
        b.append(tmp.key);
        return b.toString();
    }
    static <T> ListNode<T> reverse(ListNode<T> head) {
        if (head == null)
            return null;
        ListNode<T> curr = head;
        ListNode<T> prev = null;
        while (curr.next != null) {
            ListNode<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr.next = prev;
        return curr;
    }
    static <T> ListNode<T> recReverse(ListNode<T> node) {
        if (node != null && node.next != null) {
            ListNode<T> tail = recReverse(node.next);
            node.next.next = node;
            node.next = null; // critical
            return tail;
        }
        return node;
    }
    private static <T> void recReverseAccInternal(ListNode<T> node, ListNode<T> acc) {
        if (node != null) {
            ListNode<T> next = node.next;
            node.next = acc.next;
            acc.next = node;
            recReverseAccInternal(next, acc);
        }
    }
    private static<T> void reverseAccNoTailCallInternal(ListNode<T> node, ListNode<T> acc) {
        while (node != null) {
            ListNode<T> next = node.next;
            node.next = acc.next;
            acc.next = node;
            node = next;
        }
    }
    static <T> ListNode<T> reverseAcc(ListNode<T> node) {
        ListNode<T> acc = new ListNode<T>(null); // dummy key
        recReverseAccInternal(node, acc);
        return acc.next;
    }
    static <T>  ListNode<T> reverseAccNoTailCall(ListNode<T> node) {
        ListNode<T> acc = new ListNode<T>(null); // dummy key
        reverseAccNoTailCallInternal(node, acc);
        return acc.next;
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
