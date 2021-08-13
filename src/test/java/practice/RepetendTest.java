package practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static practice.Repetend.repetend;
import static practice.euler.PrimeOrdinal.isPrime;

public class RepetendTest {

    @Test
    public void largeReciprocal() {
        Repetend.Decimal r = repetend(1, 998001);
        System.out.println("non repeated len: " + r.nonRepString().length());
        System.out.println("repeated len: " + r.repString().length());
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
            System.out.println("i: " + i + ", reciprocal rep-len: " + d.repString().length() + ", 1/" + i + " = " + d);
            if (d.repString().length() > max) {
                max = i;
            }
        }
        Repetend.Decimal rep = repetend(1, max);
        System.out.println("max: " + max + " -- " + rep);
//        System.out.println("repetend length: " + rep.r.size());
        System.out.println("is it prime? -- " + isPrime(max));
    }

    @Test
    public void someBasicTests() {
        int n = 2;
        int d = 5;
        Repetend.Decimal repetend = repetend(n, d);
        List<Integer> expectedNonRep = Collections.singletonList(4);
        List<Integer> expectedRep = Collections.emptyList();
        assertEquals(expectedNonRep, repetend.nrep());
        assertEquals(expectedRep, repetend.rep());

        n = 12;
        d = 5;
        assertEquals(expectedNonRep, repetend.nrep());
        assertEquals(expectedRep, repetend.rep());

        n = 1;
        d = 3;
        repetend = repetend(n, d);
        expectedNonRep = Collections.emptyList();
        expectedRep = Collections.singletonList(3);
        assertEquals(expectedNonRep, repetend.nrep());
        assertEquals(expectedRep, repetend.rep());

        n = 3227; // Wikipedia example
        d = 555;
        repetend = repetend(n, d);
        expectedNonRep = Collections.singletonList(8);
        expectedRep = Arrays.asList(1, 4, 4);
        assertEquals(expectedNonRep, repetend.nrep());
        assertEquals(expectedRep, repetend.rep());
    }
}