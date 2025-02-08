package practice;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.*;
import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static practice.Fazlur.ARITHMETIC_OPERATORS;
import static practice.Permutations.*;

/**
 * Created by kmhaswade on 8/7/16.
 */
public class PermutationsTest {

    @Test
    public void testGeneratePermutationsUsingBasicExchangeNetwork() throws Exception {
        List<Integer> seq = asList(1, 5, 10);
        final Set<List<Integer>> set = new HashSet<>((int) f(seq.size()));
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

        seq = asList(1, 2);
        set.add(seq);
        set.add(asList(2, 1));
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
        List<Integer> src = asList(0, 1, 2);
        List<Integer> dest = new ArrayList<>();
        generatePermRecur(dest, src, list -> System.out.println(list));
    }

    @Test
    public void testKTuples() {
        int k = 2;
        List<String> set = List.of("+", "-");
        List<List<String>> kts = kTuples(k, set);
        System.out.println(kts);
        List<String> kt = List.of("+", "+");
        assertTrue("Does not contain: " + kt, kts.contains(kt));
        kt = List.of("+", "-");
        assertTrue("Does not contain: " + kt, kts.contains(kt));
        kt = List.of("-", "+");
        assertTrue("Does not contain: " + kt, kts.contains(kt));
        kt = List.of("-", "+");
        assertTrue("Does not contain: " + kt, kts.contains(kt));
        int expSize = 4;
        assertEquals("expected size: " + expSize, expSize, kts.size());
        System.out.println(kTuples(3, ARITHMETIC_OPERATORS));
    }

    @Test
    public void testOrderedArrangementsTuples() {
        int k = 3;
        List<Integer> set = List.of(1, 2, 3);
        List<List<Integer>> arrangements = orderedArrangements(k, set, false); // no repetition!
        System.out.println(arrangements);
        List<List<Integer>> kts = List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
        kts.forEach(kt -> assertTrue("Does not contain: " + kt, arrangements.contains(kt)));
        int expSize = 6;
        assertEquals("expected size: " + expSize, expSize, arrangements.size());
    }

    @Test
    public void testNPk() {
        int[][] perms = nPk(5, 3); // we are choosing 3 of 5: {0, 1, 2, 3, 4}
        assertEquals(60, perms.length);
        for (int[] perm : perms) {
            System.out.println(Arrays.toString(perm));
        }
    }

    @Test
    public void calculateNPKTest() {
        int n = 5, k = 1;
        long exp = 5L;
        assertEquals(n + "P" + k, exp, calculateNPK(n, k));
        k = 2; exp = 20L;
        assertEquals(n + "P" + k, exp, calculateNPK(n, k));
        k = 3; exp = 60L;
        assertEquals(n + "P" + k, exp, calculateNPK(n, k));
        k = 4; exp = 120L;
        assertEquals(n + "P" + k, exp, calculateNPK(n, k));
        k = 5; //exp is 120L
        assertEquals(n + "P" + k, exp, calculateNPK(n, k));
        k = 7; exp = 0L; // k > n
        assertEquals(n + "P" + k, exp, calculateNPK(n, k));
    }
}