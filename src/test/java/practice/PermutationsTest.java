package practice;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.*;
import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static practice.Permutations.*;

/**
 * Created by kmhaswade on 8/7/16.
 */
public class PermutationsTest {

    @Test
    public void testGeneratePermutationsUsingBasicExchangeNetwork() throws Exception {
        List<Integer> seq = asList(1, 5, 10);
        Set<List<Integer>> set = new HashSet<>((int) f(seq.size()));
        set.add(seq);
        set.add(asList(5, 1, 10));
        set.add(asList(5, 10, 1));
        set.add(asList(10, 5, 1));
        set.add(asList(10, 1, 5));
        set.add(asList(1, 10, 5));
        generatePermutationsUsingBasicExchangeNetwork(seq, next -> {
            System.out.println(next);
            assertTrue(set.contains(next));
            set.remove(next);
        });
        assertTrue(set.isEmpty());
    }

    @Test
    public void testF() throws Exception {
        assertEquals(1, f(1));
        assertEquals(120, f(5));
        assertEquals(3628800, f(10));
    }
    @Test
    public void testNext() {
        // expected_next, actual_next(given)
        assertEquals(asList('a', 'c', 'b'), nextPermutation(asList('a', 'b', 'c')));
        assertEquals(asList('c', 'a', 'b', 'd'), nextPermutation(asList('b', 'd', 'c', 'a')));
        assertEquals(EMPTY_LIST, nextPermutation(asList('d', 'c', 'b', 'a')));
        assertEquals(asList('b', 'd', 'a', 'c', 'e'), nextPermutation(asList('b', 'c', 'e', 'd', 'a')));
    }
    @Test
    public void testPervious() {
        assertEquals(asList(1, 6, 5, 3), previousPermutation(asList(3, 1, 5, 6)));
        assertEquals(asList(3, 5, 6, 1), previousPermutation(asList(3, 6, 1, 5)));
    }
    @Test
    public void testPermutationsRecur() {
        List<Integer> src = asList(1, 1, 3);
        List<Integer> dest = new ArrayList<>();
        generatePermRecur(dest, src, list -> System.out.println(list));
    }
}