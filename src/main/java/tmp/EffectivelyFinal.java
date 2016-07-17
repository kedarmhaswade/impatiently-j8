package tmp;

/**
 * Created by kmhaswade on 7/15/16.
 */
public class EffectivelyFinal {

    interface Greeter {
        void greet(String name);
    }

    static void caller(Greeter g, String s) {
        g.greet(s);
    }
    public static void main(String[] args) {
        String nn = "foo";
        caller(new Greeter() {
            @Override
            public void greet(String name) {
                System.out.println("Hello " + nn);
//                nn = "bar";
            }
        }, nn);
    }
}
