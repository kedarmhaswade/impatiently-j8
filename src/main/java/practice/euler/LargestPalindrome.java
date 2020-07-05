package practice.euler;


/**
 A palindromic number reads the same both ways.
 The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

 Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class LargestPalindrome {
    /*
    Since this for only 3-digit numbers, I am just going to go through the double loop.
    For a generic n-digit numbers, perhaps we should do something more interesting.
     */

    static boolean isPalindrome(long n) {
        // n is non negative
        if (n < 10)
            return true;
        // cheating
        String ns = "" + n;
        for (int i = 0, j = ns.length() - 1; i < j; i++, j-- ) {
            if (ns.charAt(i) != ns.charAt(j))
                return false;
        }
        return true;
    }

    static int calculate() {
        int largest = 0;
        for (int i = 100; i < 1000; i++)
            for (int j = 100; j < 1000; j++) {
                int q = i * j;
                if (isPalindrome(q) && q > largest) {
                    largest = q;
//                    System.out.println("largest: " + largest + ", factors: " + i + ", " + j);
                }
            }
        return largest;
    }

    public static void main(String[] args) {
        System.out.println(calculate());
    }
}
