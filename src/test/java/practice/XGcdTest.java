package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.XGcd.xgcd;

public class XGcdTest {

    @Test
    public void bIs1() {
        int a = 11;
        int b = 1;
        int[] xgcd = xgcd(a, b);
        assertEquals(1, xgcd[2]); // the GCD
        assertEquals(0, xgcd[0]); // x
        assertEquals(1, xgcd[1]); // y
    }
    @Test
    public void aIs0() {
        int a = 0;
        int b = 10;
        int[] xgcd = xgcd(a, b);
        assertEquals(10, xgcd[2]); // the GCD
        assertEquals(0, xgcd[0]); // x
        assertEquals(1, xgcd[1]); // y
    }
    @Test
    public void aLessThanB() {
        int a = 20;
        int b = 30;
        int[] xgcd = xgcd(a, b);
        assertEquals(10, xgcd[2]); // the GCD
        assertEquals(-1, xgcd[0]); // x
        assertEquals(1, xgcd[1]); // y
    }
    @Test
    public void relativelyPrime() {
        int a = 28;
        int b = 15;
        int[] xgcd = xgcd(a, b);
        assertEquals(1, xgcd[2]); // the GCD
        assertEquals(7, xgcd[0]); // x
        assertEquals(-13, xgcd[1]); // y
    }

    @Test
    public void bookExample() {
        int a = 60;
        int b = 42;
        int[] xgcd = xgcd(a, b);
        assertEquals(6, xgcd[2]); // the GCD
        assertEquals(-2, xgcd[0]); // x
        assertEquals(3, xgcd[1]); // y
    }

}