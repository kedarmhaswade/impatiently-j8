package practice;

import java.util.*;
import java.util.Map.Entry;

/**
 * <p>
 * You are given a server log file containing billions of lines. Each line contains a number of fields.
 * For this problem, the relevant field is an ID that denotes the page that was accessed. Write
 * <ul>
 * <li> a function to read a log file line, </li>
 * <li> a function to find k most visited pages (k is a parameter) </li>
 * </ul>
 * <p>
 * Optimize for the case where calls are interleaved. You can assume that the number of distinct pages is small
 * enough
 * to fit in RAM.
 * </p>
 * <b> It’s important to address the incrementality  </b> as the calls are interleaved.
 * <p/>
 * Created by kmhaswade on 7/19/16.
 */
public class MostVisitedPages {
    /*
    This is a tricky problem and its performance is sensitive to the use of Library functions and choice
    of data structures. After much deliberation, a hash table (HashMap) and a binary search tree (TreeSet)
    look appropriate. But what goes in the hash table is important. We store the objects (references) in the
    hash table that are also the entries in the BST. This is important to quickly remove the elements and then
    add them. The height-balanced tree (like TreeSet) will preserve the sorted nature during insertion and deletion!
    There are optimizations here and there.
     */
    protected final Map<String, Entry<String, Long>> map;
    protected final TreeSet<Entry<String, Long>> tree;

    public MostVisitedPages() {
        map = new HashMap<>();
        tree = new TreeSet<>((e1, e2) -> {
            int sizeCmp = Long.compare(e2.getValue(), e1.getValue());
            return sizeCmp == 0 ? e1.getKey().compareTo(e2.getKey()) : sizeCmp;
        });
    }

    public MostVisitedPages read(String page) {
        Entry<String, Long> ne;
        if (map.containsKey(page)) {
            Entry<String, Long> oe = map.get(page);
            ne = new AbstractMap.SimpleEntry<>(page, oe.getValue() + 1);
            tree.remove(oe); // assert true
        } else {
            ne = new AbstractMap.SimpleEntry<>(page, 1L);
        }
        tree.add(ne); // assert true
        map.put(page, ne);
        return this;
    }

    public List<Entry<String, Long>> find(int k) {
        List<Entry<String, Long>> topk = new ArrayList<>(k);
        Iterator<Entry<String, Long>> itr = tree.iterator();
        while (itr.hasNext() && k-- > 0) {
            topk.add(itr.next());
        }
        return topk;
    }
}
