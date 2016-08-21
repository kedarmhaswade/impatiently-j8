package practice;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static practice.AdditiveCombinations.countBottomUp;
import static practice.AdditiveCombinations.countTopDown;

/**
 * Created by kmhaswade on 8/20/16.
 */
public class AdditiveCombinationsTest {

    @Test
    public void testCount1() throws Exception {
        Set<Integer> set = Sets.newHashSet(2, 3, 7);
        assertEquals(7L, (long) countBottomUp(set, 9));
        assertEquals(7L, (long) countTopDown(set, 9));
    }
    @Test
    public void testCount2() throws Exception {
        Set<Integer> set = Sets.newHashSet(1, 2, 3);
        assertEquals(13L, (long) countBottomUp(set, 5));
        assertEquals(13L, (long) countTopDown(set, 5));
    }
    @Test
    public void testCount3() throws Exception {
        Set<Integer> set = Sets.newHashSet(2, 3, 7);
        assertEquals(1185739173L, (long) countBottomUp(set, 222));
        assertEquals(1185739173L, (long) countTopDown(set, 222));
    }

}