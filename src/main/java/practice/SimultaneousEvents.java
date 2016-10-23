package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *     Imagine that an event has a start time and a finish time (> start time). Given a set of end
 *     points, how would you determine the maximum number of simultaneously occurring events?
 * </p>
 * <p>
 *     The crucial idea is that of sorting. We should entertain the notion of an Event and
 *     an Endpoint. The situation changes only at the Endpoints.
 * </p>
 * Created by kedar on 10/21/16.
 */
public class SimultaneousEvents {
     static class Event {
        final int start;
        final int finish;
        Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }
     static class Endpoint {
        final int time;
        final boolean isStartOfEvent;
        Endpoint(int time, boolean isStartOfEvent) {
            this.time = time;
            this.isStartOfEvent = isStartOfEvent;
        }
        static Comparator<Endpoint> EVENT_EP_COMPARATOR = (Endpoint e1, Endpoint e2) -> {
            int tc = Integer.compare(e1.time, e2.time);
            return tc == 0 ? e1.isStartOfEvent ? -1 : 1 : tc;
        };
    }
    static int maxCount(List<Event> events) {
        List<Endpoint> endpoints = new ArrayList<>(events.size());
        for (Event e : events) {
            endpoints.add(new Endpoint(e.start, true));
            endpoints.add(new Endpoint(e.finish, false));
        }
        endpoints.sort(Endpoint.EVENT_EP_COMPARATOR);
        int count = 0, max = 0;
        for (Endpoint ep : endpoints) {
            count = ep.isStartOfEvent ? count + 1 : count - 1;
            max = Math.max(max, count);
        }
        return max;
    }
}
