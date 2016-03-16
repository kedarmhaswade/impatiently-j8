package tmp;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** Proxy-based attempt to: http://stackoverflow.com/questions/35805928/java-offer-a-filtered-view-on-a-class
 * Created by kmhaswade on 3/4/16.
 */
interface BooleanArray {
    boolean getDataAt(int i);
    BooleanArray opposite();
}
public class Booleans implements BooleanArray {
    private final boolean[] data;
    private volatile BooleanArray OPPOSITE;

    public Booleans (boolean[] data) {
        this.data = data;
    }

    private Object initOpposite(BooleanArray target) {
        return Proxy.newProxyInstance(Booleans.class.getClassLoader(),
                new Class[]{BooleanArray.class},
                new NegationHandler(target));
    }

    public boolean getDataAt(int i) {
        return data[i];
    }

    public BooleanArray opposite() {
        if (OPPOSITE == null)
            OPPOSITE = (BooleanArray) initOpposite(this);
        return OPPOSITE;
    }

    public static void main(String[] args) {
        BooleanArray ab = new Booleans(new boolean[]{true, false, true});
        BooleanArray ba = ab.opposite();
        BooleanArray aba = ba.opposite();
        for (int i = 0; i < 3; i ++)
            System.out.println(ab.getDataAt(i) + ", and, " + ba.getDataAt(i) + ", and, " + aba.getDataAt(i) + " again!");
    }
    private static final class NegationHandler implements InvocationHandler {
        private final BooleanArray target;
        public NegationHandler(BooleanArray target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("getDataAt".equals(method.getName())) {
                Boolean orig = (Boolean) method.invoke(target, args);
                if (orig)
                    return Boolean.FALSE;
                return Boolean.TRUE;
            }
            return method.invoke(target, args);
        }
    }
}