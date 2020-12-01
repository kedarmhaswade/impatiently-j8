package tmp;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Random;

import static tmp.ClassicPartition.partition;

public class ClassicPartitionTest extends TestCase {

    @Test public void testSorted() {
        int a[] = {1, 2, 3, 4};
        int pi = partition(a);
        testInner(a, pi);
    }
    @Test public void testRandom() {
        int a[] = getRandom(100);
        int pi = partition(a);
        System.out.println("pi: " + pi);
        testInner(a, pi);
    }

    private int[] getRandom(int cap) {
        Random r = new Random(1234); // fixed seed
        int[] a = new int[cap];
        for (int i = 0; i < cap; i++) {
            a[i] = r.nextInt();
        }
        return a;
    }

    private void testInner(int[]a, int pi) {
        for (int i = 0; i < pi; i++) {
            assertTrue("less than invariant holds: i = " + i + ", a[i] = " + a[i],
                a[i] < a[pi]);
        }
        for (int i = pi+1; i < a.length; i++) {
            assertTrue("greater than or equal to invariant holds: i = " + i + ", a[i] = " + a[i],
                a[i] >= a[pi]);
        }
    }
}