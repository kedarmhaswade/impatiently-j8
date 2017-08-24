package practice;

import com.google.common.collect.Range;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static practice.ConsecutiveSum.consecutive;

public class ConsecutiveSumTest {

    public void testInner(int num, int expectedWays) {
        List<Range<Integer>> ranges = new ArrayList<>(4);
        int actualWays = consecutive(num, ranges);
//        System.out.println(actualWays);
        assertTrue("There are " + expectedWays + " ways to sum to: " + num, actualWays == expectedWays);
        for (Range r : ranges) {
//            System.out.println(r);
            long actual = sumIntsInArithmeticProgression(r, 1);
//            System.out.println(actual);
            assertTrue("sum is: " + num, (long) num == actual);
        }
    }
    @Test
    public void test15() {
        testInner(15, 3);
    }
    @Test
    public void test3() {
        testInner(3, 1);
    }

    @Test
    public void test4() {
        testInner(4, 0);
    }

    @Test
    public void test5() {
        testInner(5, 1);
    }

    @Test
    public void testMillion() {
        testInner(1_000_000, 6);
    }


    private long sumIntsInArithmeticProgression(Range r, int d) {
        int lowIn = Integer.class.cast(r.lowerEndpoint());
        int highIn = Integer.class.cast(r.upperEndpoint());
        int nTerms = (highIn - lowIn) / d + 1;
        return (Math.multiplyExact((lowIn + highIn), nTerms)) >>> 1;
    }
}
