package practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static practice.PartitionFunction.nonIncreasing;

/**
 * Created by kedar on 10/11/16.
 */
public class PartitionFunctionTest {
    @Test
    public void nonIncreasingTest() throws Exception {
        int n = 10;
        LinkedList<Integer> rem = new LinkedList<>();
        List<List<Integer>> acc = new ArrayList<>(64);
        nonIncreasing(n, rem, acc);
        assertEquals(42L, acc.size());
        acc.forEach(list -> assertEquals(n, (long) list.stream().reduce(Integer::sum).get())); // deliberate non-use of ifPresent
    }

}