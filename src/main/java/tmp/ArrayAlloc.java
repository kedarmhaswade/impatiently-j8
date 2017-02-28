package tmp;

/**
 * Created by kedar on 1/4/17.
 */
public class ArrayAlloc {
    public static void main(String[] args) {
//        int[] ints = new int[2_000_000_000];
        int[] ints = new int[Integer.MAX_VALUE];
        System.out.println(ints.length);
    }
}
