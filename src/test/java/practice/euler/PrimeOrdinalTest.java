package practice.euler;


import org.junit.Test;

public class PrimeOrdinalTest {

    @Test
    public void isPrime() {
        final long f5 = (1L << 32) + 1L;
        System.out.println(f5 + " is " + (PrimeOrdinal.isPrime(f5) == -1 ? "prime" : "not prime"));
        System.out.println(PrimeOrdinal.isPrime(2 * 3 * 5 * 7 * 11 + 1L));
        System.out.println(PrimeOrdinal.isPrime(2 * 3 * 5 * 7 * 11 * 13 + 1L));
    }
}