package practice;

import org.junit.Test;

import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static practice.Sylvester.*;

public class SylvesterTest {


    @Test
    public void testGcdChainBasic() {
        long e = 10;
        long a = euclid(10, 20, 30);
        assertEquals(format("GCD expected: %d, actual: %d%n", e, a), e, a);
    }

    @Test
    public void testGcdPrimes() {
        long e = 1;
        long a = euclid(7, 11, 19, 997);
        assertEquals(format("GCD expected: %d, actual: %d%n", e, a), e, a);
    }

    @Test
    public void testGcdSomeCommonFactors() {
        long e = 1;
        long a = euclid(4, 6, 21, 98);
        assertEquals(format("GCD expected: %d, actual: %d%n", e, a), e, a);
    }

    @Test
    public void testLcmChainBasic() {
        long e = 60;
        long a = lcm(10, 20, 30);
        assertEquals(format("LCM expected: %d, actual: %d%n", e, a), e, a);
    }

    @Test
    public void testLcmChainPairwisePrime() {
        long e = 11*13*17;
        long a = lcm(11, 13, 17);
        assertEquals(format("LCM expected: %d, actual: %d%n", e, a), e, a);
    }

    @Test
    public void testUnitFactors1() {
        List<Long> e = List.of(2L, 12L, 420L);
        long n = 41;
        long d = 70;
        List<Long> units = ufs(n, d); // 41/70 as a sum of unit fractions
        assertEquals(format("Unit fraction denominators expected: %s, actual:%s%n", e, units), e, units);
        System.out.println(toStr(n, d, units));
        assertTrue("verification fails!", verify(n, d, e));
    }
    @Test
    public void testUnitFactors2() {
        List<Long> e = List.of(2L, 3L, 7L, 105L);
        long n = 69;
        long d = 70;
        List<Long> units = ufs(n, d); // 69/70 as a sum of unit fractions
        assertEquals(format("Unit fraction denominators expected: %s, actual:%s%n", e, units), e, units);
        System.out.println(toStr(n, d, units));
        assertTrue("verification fails!", verify(n, d, e));
    }

    @Test
    public void testUf() {
        List<Long> e = List.of(2L);
        long n = 1;
        long d = 2;
        List<Long> units = ufs(n, d);
        assertEquals(format("Unit fraction denominators expected: %s, actual:%s%n", e, units), e, units);
        System.out.println(toStr(n, d, units));
        assertTrue("verification fails!", verify(n, d, e));
    }
    @Test
    public void testUfUnreduced() {
        List<Long> e = List.of(2L);
        long n = 1L<<40;
        long d = 1L<<41;
        List<Long> units = ufs(n, d);
        assertEquals(format("Unit fraction denominators expected: %s, actual:%s%n", e, units), e, units);
        System.out.println(toStr(n, d, units));
        assertTrue("verification fails!", verify(n, d, e));
    }

    @Test
    public void test1() {
        List<Long> e = List.of(1L);
        long n = 2;
        long d = 2;
        List<Long> units = ufs(n, d);
        assertEquals(format("Unit fraction denominators expected: %s, actual:%s%n", e, units), e, units);
        System.out.println(toStr(n, d, units));
        assertTrue("verification fails!", verify(n, d, e));
    }
    @Test (expected = IllegalArgumentException.class)
    public void testDenominator0() {
        List<Long> e = List.of(1L);
        long n = 0;
        long d = 0;
        List<Long> units = ufs(n, d);
        System.out.println(toStr(n, d, units));
    }
    @Test (expected = UnsupportedOperationException.class)
    public void testImproper() {
        List<Long> e = List.of(1L);
        long n = 5;
        long d = 3;

        List<Long> units = ufs(n, d);
        System.out.println(toStr(n, d, units));
    }
    @Test (expected = IllegalArgumentException.class)
    public void testDenNegative() {
        List<Long> e = List.of(1L);
        long n = -4;
        long d = 7;
        List<Long> units = ufs(n, d);
        System.out.println(toStr(n, d, units));
    }
    @Test
    public void testNegatives() {
        List<Long> e = List.of(1L);
        long n = -4;
        long d = -7;
        List<Long> units = ufs(n, d);
        assertEquals(2L, units.get(0).longValue());
        assertEquals(14L, units.get(1).longValue());
    }

}