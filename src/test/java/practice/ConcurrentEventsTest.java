package practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static practice.ConcurrentEvents.Event;

/**
 * Created by kmhaswade on 8/16/16.
 */
public class ConcurrentEventsTest {

    @Test
    public void testNmax() throws Exception {
        Event e1 = new Event(0, 2);
        Event e2 = new Event(3, 4);
        Event e3 = new Event(0, 3);
        Event e4 = new Event(2, 4);
        Event e5 = new Event(0, 2);
        Event e6 = new Event(3, 5);
        Event e7 = new Event(2, 3);
        Event e8 = new Event(3, 6);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);
        events.add(e5);
        events.add(e6);
        events.add(e7);
        events.add(e8);
        ConcurrentEvents ce = new ConcurrentEvents(events);
        assertEquals(4L, ce.nMax());

    }
}