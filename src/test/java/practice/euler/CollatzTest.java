package practice.euler;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static practice.euler.Collatz.findLongestChain;
import static practice.euler.Collatz.findLongestChainIter;
import static practice.euler.Collatz.getChainLength;

/**
 * Created by kedar on 11/2/16.
 */
public class CollatzTest {
    @Test
    public void findLongestChainTest() {
        int limit = 10_000;
        Map<Integer, Integer> cache = new HashMap<>(limit);
        assertEquals(findLongestChainIter(limit), findLongestChain(limit, cache));
    }
    @Test
    public void findLongestChainTestIter() {
        int limit = 20;
        System.out.println(Collatz.findLongestChainIter(limit));
    }
    @Test
    public void getChainLengthTest() {
        assertEquals(1L, getChainLength(1L));
        assertEquals(2L, getChainLength(2L));
        assertEquals(8L, getChainLength(3L));
        assertEquals(3L, getChainLength(4L));
        assertEquals(6L, getChainLength(5L));
        assertEquals(9L, getChainLength(6L));
        assertEquals(17L, getChainLength(7L));
        assertEquals(4L, getChainLength(8L));
        assertEquals(20L, getChainLength(9L));
        assertEquals(7L, getChainLength(10L));
        assertEquals(11L, getChainLength(1024L));
        assertEquals(21L, getChainLength(1L<<20));
    }
}