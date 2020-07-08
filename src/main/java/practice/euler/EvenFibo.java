package practice.euler;

/**
 Each new term in the Fibonacci sequence is generated by adding the previous
 two terms. By starting with 1 and 2, the first 10 terms will be:

 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

 By considering the terms in the Fibonacci sequence whose values do not exceed
 four million, find the sum of the even-valued terms.
 */
public class EvenFibo {
    static final int limit = 4_000_000;
    static long sumEvens(int f, int s, long prev) {
        if (f > limit) {
            return prev;
        }
        if (f % 2 == 0) {
            prev += f;
        }
        return sumEvens(s, f+s, prev);
    }

    public static void main(String[] args) {
        System.out.println(sumEvens(1, 2, 0));
    }
}