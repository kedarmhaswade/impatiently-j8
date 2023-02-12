package practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static practice.QthRoot.pow;
import static practice.QthRoot.qthRootOfP;

public class QthRootTest {

    @Test
    public void testPower() {
        long n = 2;
        int p = 10;
        System.out.println(pow(n, p));
    }

    @Test
    public void root2Test() {
        long p = 2;
        int q = 2;
        long m = 1;
        long n = 1;
        qthRootOfP(p, q, m, n, 6, (a, b) -> System.out.println(a + "/" + b + " = " + a * 1.0 / b));
    }

}