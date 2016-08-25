package tmp;

/**
 * Created by kmhaswade on 8/22/16.
 */
public class ArrayStoreExceptionDemo {
    static class Pair<F, S> {
        final F f;
        final S s;
        Pair(F f, S s) {
            this.f = f;
            this.s = s;
        }
        @Override
        public String toString() {
            return f + ", " + s;
        }
    }
    static class Name extends Pair<String, String> {
        Name(String f, String s) {
            super(f, s);
        }
    }
    public static void main(String[] args) {
        // fine because of covariance, where Pair<String, String>[] is required, we pass Name[],
        // as Name extends Pair<String, String>
        Pair<String, String>[] pairs = new Name[10];
        pairs[0] = new Pair<>("James", "Bond");
        // but fails at runtime
    }
}
