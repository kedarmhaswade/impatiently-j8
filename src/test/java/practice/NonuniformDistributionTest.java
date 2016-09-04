package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kmhaswade on 9/3/16.
 */
public class NonuniformDistributionTest {

    @Test
    public void testNext() throws Exception {
        // testing code involving random numbers is tricky.
        int[] n = new int[] {1, 3, 4, 6};
        double[] p = new double[] {0.5, 0.3, 0.1, 0.1}; // sum should be 1.
        int len = n.length;
        assert len == p.length;
        NonuniformDistribution nd = new NonuniformDistribution(n, p);
        int nIter = 50_000_000; // i.e. 50 million times!
        int[] histo = new int[len];
        for (int i = 0; i < nIter; i++) {
            histo[nd.nextIndex()] += 1;
        }
        for (int i = 0; i < len; i++) {
//            System.out.printf("%f, ", (histo[i]*1.0d /nIter));
            assertEquals(0.0d, Math.abs(p[i] - histo[i]*1.0d /nIter), 0.0005);
        }
    }
}