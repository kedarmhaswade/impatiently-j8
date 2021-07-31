package practice;

import org.junit.Test;

import static practice.Repetend.repetend;
import static practice.euler.PrimeOrdinal.isPrime;

public class RepetendTest {

    @Test
    public void largeReciprocal() {
        Repetend.Decimal r = repetend(1, 998001);
        System.out.println("non repeated len: " + r.nrep().length());
        System.out.println("repeated len: " + r.rep().length());
        System.out.println(r);
    }

    @Test
    public void repetendTest() {
        System.out.println(repetend(3227, 555));
        System.out.println(repetend(1, 7));
        System.out.println(repetend(1, 11));
        System.out.println(repetend(1, 12));
        System.out.println(repetend(1, 13));
        System.out.println(repetend(1, 1));
        System.out.println(repetend(1, 2));
    }

    @Test
    public void longestRepUnitFraction() {
        int max = 0;
        int lim = 100;
        for (int i = 1; i <= lim; i++) {
            Repetend.Decimal d = repetend(1, i);
            System.out.println("i: " + i + ", reciprocal rep-len: " + d.rep().length() + ", 1/" + i + " = " + d);
            if (d.rep().length() > max) {
                max = i;
            }
        }
        Repetend.Decimal rep = repetend(1, max);
        System.out.println("max: " + max + " -- " + rep);
//        System.out.println("repetend length: " + rep.r.size());
        System.out.println("is it prime? -- " + isPrime(max));
    }
}