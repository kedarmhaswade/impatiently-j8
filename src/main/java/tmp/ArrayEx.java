package tmp;

/**
 * Created by kmhaswade on 2/19/16.
 */
public class ArrayEx {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        for (int i : ints)
            System.out.println(i);
        //ints.forEach(System.out::println);
    }
}
