package tmp;



/**
 * Created by kmhaswade on 8/19/16.
 */
public class X {
    public static void main(String[] args) {
        Number n = new Long(200L);
        Class<? extends Number> c = n.getClass();
    }
}
