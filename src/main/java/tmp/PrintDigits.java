package tmp;

/**
 * Created by kmhaswade on 7/27/16.
 */
public class PrintDigits {
    static void print(long n) {
        if (n < 10)
            System.out.print(n);
        else {
            print(n / 10);
            System.out.print(n % 10);
        }
    }

    public static void main(String[] args) {
        print(1345);
    }
}
