package practice;

import java.util.*;
import java.util.function.Consumer;

/**
 * <p>
 *     This problem, in spite of its superficial similarity with {@linkplain TopK}, is quite
 *     different. There is a stream of records (each has an ID) that your program should read
 *     and whenever queried, it should return the top k most frequently occurring records at
 *     the time of querying.
 * </p>
 * <p>
 *     Thus, your program should support two operations:
 *     <ul>
 *         <li> read (record) -- reads and processes the given record</li>
 *         <li> query(k) -- returns the top k most frequently occurring records at the time of query</li>
 *     </ul>
 * </p>
 * <p>
 *     The insertion and deletion suggests use of BST, and since the update of an existing node
 *     in a BST disturbs the balance, the trick is to use a hash table for quicker lookup. We
 *     store the BST nodes in the hash table.
 * </p>
 * Created by kedar on 11/4/16.
 */
public class StreamingTopK<K> {

    public static final class CountNode<E> { // does not implement Comparable<CountNode>
        final E key;
        final long count;
        public CountNode(E key, long count) {
            if (key == null)
                throw new IllegalArgumentException("null key not allowed");
            this.key = key;
            if (count <= 0)
                throw new IllegalArgumentException("countFastRecursive must be positive: " + count);
            this.count = count;
        }

        public CountNode(E key) {
            this(key, 1L);
        }

        @Override
        public String toString() {
            return key + ": " + count;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof CountNode) {
                @SuppressWarnings("unchecked")
                CountNode<E> that = (CountNode) o;
                return this.count == that.count && this.key.equals(that.key);
            }
            return false;
        }
    }

    private final Map<K, CountNode<K>> map;
    // the record with the highest frequency comes first
    private final TreeSet<CountNode<K>> set =
            new TreeSet<>((n1, n2) -> {
                int nc = Long.compare(n2.count, n1.count);
                return nc == 0 ? (n1.equals(n2) ? 0 : -1) : nc;
            });

    public StreamingTopK(int estimatedMax) {
        map = new HashMap<>(estimatedMax); // help reduce rehashing
    }

    public void read(K record) {
        CountNode<K> node = map.get(record); // quick lookup of the node
//        System.out.println("node: " + node);
        if (node == null) {
            CountNode<K> newNode = new CountNode(record);
            map.put(record, newNode);
            boolean shouldAdd = set.add(newNode); // we know that since map doesn't have it, set doesn't have it
            assert shouldAdd : "strange! node with key: " + record  + " already exists in the set";
        } else {
            boolean shouldRemove = set.remove(node); // set must contain it
            assert shouldRemove : "strange! node with key: " + record + " is not in the set";
            CountNode<K> next = new CountNode(record, Math.addExact(node.count, 1));
            map.put(record, next); // take care of map
            boolean addNext = set.add(next); // take care of set
            assert addNext : "strange, CountNode with countFastRecursive: " + next.count + " could not be added!";
        }
    }
    public void query(int k, Consumer<CountNode<K>> consumer) {
        Iterator<CountNode<K>> iter = set.iterator();
        while (k-- > 0 && iter.hasNext())
            consumer.accept(iter.next());
    }
}
