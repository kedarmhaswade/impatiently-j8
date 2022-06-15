package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.NChooseK.nck;
import static practice.NChooseK.nckFast;

/**
 * Created by kmhaswade on 8/23/16.
 */
public class NChooseKTest {

    @Test
    public void testNck() throws Exception {
        assertEquals(120L, nck(10, 7));
        assertEquals(3003L, nck(15, 5));
        assertEquals(10272278170L, nck(50, 40));
    }
    @Test
    public void testNckFast() throws Exception {
        assertEquals(120L, nckFast(10, 7));
        assertEquals(3003L, nckFast(15, 5));
        assertEquals(10272278170L, nckFast(50, 40));
        System.out.println(nckFast(16, 4));
        System.out.println(nckFast(64, 8));
    }
}