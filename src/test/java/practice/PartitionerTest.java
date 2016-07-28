package practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static practice.Partitioner.naivePartition;
import static practice.Partitioner.partition;

/**
 * Created by kmhaswade on 7/27/16.
 */
public class PartitionerTest {

    @Test
    public void testNaivePartition1()  {
        int[] a = {4, 2, 1, 5, 8, 4, 0};
        int p = naivePartition(a, 0);  // naivePartition around a[0] = 4
        System.out.println(Arrays.toString(a) + ", p: " + p + ", a[p]: " + a[p]);
        int pivot = a[p];
        for (int i = 0; i <= p; i++) {
            assertTrue(a[i] <= pivot);
        }
        for (int i = p + 1; i < a.length; i++) {
            assertTrue(a[i] > pivot);
        }
    }
    @Test
    public void testPartition1()  {
        int[] a = {3, 4, 5, 1, 2, 3, 3};
        int p = partition(a, 0);  // naivePartition around a[0] = 4
        System.out.println(Arrays.toString(a) + ", p: " + p + ", a[p]: " + a[p]);
        int pivot = a[p];
        for (int i = 0; i < p; i++) {
            assertTrue(a[i] < pivot);
        }
        for (int i = p + 1; i < a.length; i++) {
            assertTrue(a[i] >= pivot);
        }
    }
    @Test
    public void testEqual() {
        int len = 100;
        int[] a = new int[len];
        for (int i = 0; i < len; i++)
            a[i] = 2;
        int p = partition(a, len - 1); // partition around the last element
        int pivot = a[p];
        for (int i = 0; i < p; i++) {
            assertTrue(a[i] < pivot);
        }
        for (int i = p + 1; i < len; i++) {
            assertTrue(a[i] >= pivot);
        }
    }
    @Test
    public void testAlternating() {
        int[] a = {2, 1, 4, 3, 9, 3, 10, 3, 9, 3};
        int len = a.length;
        int p = partition(a, len - 1);
        System.out.println(Arrays.toString(a) + ", p: " + p + ", a[p]: " + a[p]);
        int pivot = a[p];
        for (int i = 0; i < p; i++) {
            assertTrue(a[i] < pivot);
        }
        for (int i = p + 1; i < len; i++) {
            assertTrue(a[i] >= pivot);
        }
    }

    private static Map<Integer, Integer> getHisto(int[] a) {
        Map<Integer, Integer> histo = new HashMap<>(a.length);
        for (int k : a) {
            int v = histo.containsKey(k) ? histo.put(k, histo.get(k) + 1) : 1;
        }
        return histo;
    }
}