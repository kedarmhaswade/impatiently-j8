package tmp;

/**
 * Created by kedar on 12/22/16.
 */
public class Limits {
    public static void main(String[] args) {
        System.out.println("Is -1 << 31 == Integer.MIN_VALUE? -- " + ((-1 << 31) == Integer.MIN_VALUE));
        System.out.println("Is (1 << 31) - 1 == Integer.MAX_VALUE? -- " + ((-1 << 31) - 1 == Integer.MAX_VALUE));

        float f = 16777216;
        System.out.println("is f == f + 1 ? : " + (f == (f + 1)));
    }
}
