package tmp;

/**
 * Created by kedar on 5/19/17.
 */
public class SmallestFloat {
    public static void main(String[] args) {
        float f = 1<<24;
        System.out.println(f == (f + 1));
    }
}
