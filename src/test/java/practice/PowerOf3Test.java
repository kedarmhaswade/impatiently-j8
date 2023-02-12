package practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static practice.PowerOf3.isPowerOf3;

public class PowerOf3Test {

    @Test
    public void basic() {
        int x = 1;
        assertTrue(isPowerOf3(x));

        x = 3;
        assertTrue(isPowerOf3(x));

        x = 6;
        assertFalse(isPowerOf3(x));

        x = (int) QthRoot.pow(3L, 19);
        System.out.println(x);
        assertTrue(isPowerOf3(x));

    }

}