package practice;

import org.junit.Test;
import practice.SimultaneousEvents.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static practice.SimultaneousEvents.maxCount;

/**
 * Created by kedar on 10/21/16.
 */
public class SimultaneousEventsTest {
    @Test
    public void maxCountTest() throws Exception {
        int[] e = new int[] {1, 5, 6, 10, 11, 13, 14, 15, 2, 7, 8, 9, 12, 15, 4, 5, 9, 17, 10, 11};
        List<Event> events = new ArrayList<>(e.length / 2);
        for (int i = 0; i < e.length; i += 2) {
            events.add(new Event(e[i], e[i + 1]));
        }
        System.out.println(maxCount(events));
    }

}