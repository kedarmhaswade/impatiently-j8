package tmp;

/**
 * <p>
 *     What happens when autoboxed int is compared to a 'new'ed Integer whose
 *     value is cached by the JVM (by default, -128 to 127 are cached)?
 * </p>
 * Created by kedar on 3/10/17.
 */
public class AutoboxAndNewCachedInteger {
    public static void main(String[] args) {
        Integer a = new Integer(100);
        Integer b = 100;
        System.out.println(a == b);
    }
}
