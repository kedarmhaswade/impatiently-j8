package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Given a set of events, we want to find the maximum number of concurrently happening events.
 * </p>
 * <p>
 *     The first idea we must realize to solve this problem is that the number of simultaneously occurring events
 *     changes only at the endpoints of those intervals. Sorting of the events on endpoints helps improve the
 *     running time. The modeling of the problem in terms of endpoints and events and then making a sweep of all
 *     the endpoints while maintaining the (max of) number of events an endpoint is a helpful technique.
 * </p>
 * Created by kmhaswade on 8/16/16.
 */
public class ConcurrentEvents {
    private final List<Event> events;

    public ConcurrentEvents(List<Event> events) {
        this.events = events;
    }
    public static class Event {
        final int start;
        final int end;
        public Event(int start, int end) {
            // start <= end
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
    public static class Endpoint implements Comparable<Endpoint> {
        private final Event e;
        private final boolean isStart;
        public Endpoint(Event e, boolean isStart) {
            this.e = e;
            this.isStart = isStart;
        }
        @Override
        public int compareTo(Endpoint that) {
            int v1 = this.isStart ? this.e.start : this.e.end;
            int v2 = that.isStart ? that.e.start : that.e.end;
            int c = Integer.compare(v1, v2);
            if (c != 0)
                return c;
            // both of them have same value
            if (this.isStart && that.isStart)
                return 0; // break ties arbitrarily
            if (!this.isStart)
                return -1; // if this event ends at the time that starts, this comes before
            return 1; // else this comes after
        }
        @Override
        public String toString() {
            return "" + (isStart ? e.start : e.end);
        }
    }
    public int nMax() {
        List<Endpoint> endpoints = new ArrayList<>(2 * events.size());
        for (Event event : events) {
            Endpoint s = new Endpoint(event, true);
            Endpoint e = new Endpoint(event, false);
            boolean added = endpoints.add(s);
            assert added;
            added = endpoints.add(e);
            assert(added);
        }
        endpoints.sort(null); // use endpoints' natural ordering dictated by implementation of Comparable
        int max = 0;
        int num = 0;
        for (Endpoint ep : endpoints) {
            System.out.println("ep: " + ep);
            if (ep.isStart) {
                num += 1;
                max = Math.max(num, max); // we could also take a snapshot of events
//                System.out.println("inc current num: " + num + ", current max: " + max);
            } else {
                assert ! ep.isStart;
                num -= 1;
//                System.out.println("dec current num: " + num + ", current max: " + max);
            }
        }
        return max;
    }
}
