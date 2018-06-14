package tmp;

import javax.annotation.Nonnull;

public class CCE {
    private static <T> T convert(@Nonnull Object v, String what) {
        try {
//            @SuppressWarnings("unchecked")
            T result = (T) v;
            return result;
        } catch (ClassCastException e) {
            String target = v.getClass().getSimpleName();
            String msg = "Error converting " + what + " = " +
                v.toString() + " to " + target;
            throw new Error(msg, e);
        }
    }

    public static void main(String[] args) {
        String x = convert(1, "foo");
        System.out.println(x);
    }
    private static String tryTry(Object o) {
        try {
            String s = (String) o;
            return s;
        } catch (ClassCastException c) {
            return null;
        }
    }
}
