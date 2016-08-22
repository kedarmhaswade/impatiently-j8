package practice;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.LeaderChange.maxTimesAllCases;
import static practice.LeaderChange.maxTimesCurrentScoreBased;

/**
 * Created by kmhaswade on 8/21/16.
 */
public class LeaderChangeTest {

    @Ignore
    @Test
    public void testMaxTimes() throws Exception {
        // for a score of 10-6, with play points: [2, 3, 7], the max number of times
        // the change in leadership happens is 4: 0-0 -> 2-0 -> 2-3 (1) -> 4-3 (2) -> 4-6 (3) -> 7-6 (4) -> 10-6 (4).
        assertEquals(4L, (long) maxTimesCurrentScoreBased(10, 6, new int[] {2, 3, 7}));
        assertEquals(4L, (long) maxTimesAllCases(10, 6, new int[] {2, 3, 7}));
    }
}