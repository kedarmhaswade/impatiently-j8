package practice;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.Assert.*;
import static practice.Subsets.getPowersetIteratively;

/**
 * Created by kedar on 10/7/16.
 */
public class SubsetsTest {
    @Test
    public void intSubsets() {
        Set<Integer> set = Sets.newHashSet(1, 2, 3);
        Set<Set<Integer>> ps = getPowersetIteratively(set);
        assertEquals(8L, ps.size());
        assertTrue(ps.contains(emptySet()));
        assertTrue(ps.contains(Sets.newHashSet(1)));
        assertTrue(ps.contains(Sets.newHashSet(1, 2)));
        assertTrue(ps.contains(Sets.newHashSet(1, 3)));
        assertTrue(ps.contains(Sets.newHashSet(1, 2, 3)));
        assertTrue(ps.contains(Sets.newHashSet(2)));
        assertTrue(ps.contains(Sets.newHashSet(2, 3)));
        assertTrue(ps.contains(Sets.newHashSet(3)));
    }

    @Test
    public void stringSubsets() {
        String LARRY = "Larry";
        String MOE = "Moe";
        String CURLY = "Curly";
        Set<String> stooges = Sets.newHashSet(LARRY, MOE, CURLY);
        Set<Set<String>> ps = getPowersetIteratively(stooges);
        assertEquals(8L, ps.size());
        assertTrue(ps.contains(emptySet()));
        assertTrue(ps.contains(Sets.newHashSet(LARRY)));
        assertTrue(ps.contains(Sets.newHashSet(LARRY, MOE)));
        assertTrue(ps.contains(Sets.newHashSet(LARRY, CURLY)));
        assertTrue(ps.contains(Sets.newHashSet(LARRY, MOE, CURLY)));
        assertTrue(ps.contains(Sets.newHashSet(MOE)));
        assertTrue(ps.contains(Sets.newHashSet(MOE, CURLY)));
        assertTrue(ps.contains(Sets.newHashSet(CURLY)));
    }
}