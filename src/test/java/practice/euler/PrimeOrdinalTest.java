package practice.euler;

import org.junit.jupiter.api.Test;

import static practice.euler.PrimeOrdinal.isPrime;

import static org.junit.jupiter.api.Assertions.*;

class PrimeOrdinalTest {

    @Test
    void isPrime() {
        final long f5 = (1L << 32) + 1L;
        System.out.println(f5 + " is " + (PrimeOrdinal.isPrime(f5) == -1 ? "prime" : "not prime"));
    }
}