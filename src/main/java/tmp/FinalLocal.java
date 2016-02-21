package tmp;

/**
 * Created by kmhaswade on 2/20/16.
 */
public class FinalLocal {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        for (final int i : ints) {
            System.out.println(i);
        }
        int[] hash_a = ints;
        for (int hash_i = 0; hash_i < hash_a.length; hash_i++) {
            final int id = hash_a[hash_i];
            System.out.println(id);
        }

    }
}
