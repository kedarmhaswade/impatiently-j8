package practice;

import java.util.*;
import java.util.Map.Entry;

/**
 * <p>
 *     This problem is similar to the problem in {@linkplain MostVisitedPages}. The additional constraint is that
 *     the pages should not be more than W time units older than the most recently visited page. Thus, each page has
 *     a timestamp associated with it. The "window" of time is specified beforehand and is fixed.
 * </p>
 * <p>
 *     We use a queue to keep the most recent pages and evict the pages that are older. This is similar to the
 *     MRU cache.
 * </p>
 * Created by kmhaswade on 7/20/16.
 */
public class MostVisitedPagesInWindow extends MostVisitedPages {
    private final long w; // the window (in milliseconds?)
    private final Deque<Entry<String, Long>> q;
    public MostVisitedPagesInWindow(long w) {
        // validate w
        this.w = w;
        this.q = new ArrayDeque<>();
    }
    public MostVisitedPagesInWindow read(String page, long ts) {
        super.read(page);
        q.addFirst(new AbstractMap.SimpleEntry<>(page, ts));
        // elements are returned from tail to head!
        Iterator<Entry<String, Long>> itr = q.descendingIterator();
        Map<String, Long> expired = new HashMap<>();
        while (itr.hasNext()) {
            Entry<String, Long> e = itr.next();
            long tse = e.getValue(); // timestamp of the entry
            if ((ts - tse) > w) { // expired entry
                String epage = e.getKey();
                long count = expired.containsKey(epage) ? expired.get(epage) : 0;
                expired.put(epage, count + 1);
            } else {
                // this entry is not expired and that means all other entries (tail -> head) are so too.
                break;
            }
        }
        // adjust counts
        expired.entrySet().stream().forEach(expEntry -> {
            String expPage = expEntry.getKey();
            Entry<String, Long> curr = map.get(expPage);
            Entry<String, Long> new1 = new AbstractMap.SimpleEntry<>(expPage, curr.getValue() - expEntry.getValue());
            tree.remove(curr);
            tree.add(new1);
            map.put(expPage, new1);
        });
        return this;
    }
}
