package practice;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static practice.GatherFour.Key;
import static practice.GatherFour.Key.*;
import static practice.GatherFour.getTogether;

/**
 * Created by kmhaswade on 7/29/16.
 */
public class GatherFourTest {

    @Test
    public void testGetTogether() throws Exception {
        Key[] a = {RED, BLUE, GREEN, YELLOW, RED, RED, BLUE, BLUE, GREEN, GREEN, YELLOW, YELLOW};
        getTogether(a);
        assertSame(RED, a[0]);
        assertSame(RED, a[1]);
        assertSame(RED, a[2]);
        assertSame(BLUE, a[3]);
        assertSame(BLUE, a[4]);
        assertSame(BLUE, a[5]);
        assertSame(GREEN, a[6]);
        assertSame(GREEN, a[7]);
        assertSame(GREEN, a[8]);
        assertSame(YELLOW, a[9]);
        assertSame(YELLOW, a[10]);
        assertSame(YELLOW, a[11]);
    }
}