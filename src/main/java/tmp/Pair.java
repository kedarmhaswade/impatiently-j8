package tmp;

/**
 * Created by kmhaswade on 8/10/16.
 */
public class Pair <X, Y> {
    private final X x;
    private final Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
        System.out.println(x.getClass().getTypeName());
        System.out.println(y.getClass().getTypeName());
    }
    public X getFirst() {
        return x;
    }
    public Y getSecond() {
        return y;
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Pair<?, ?> one = new Pair<>(1L, "Foo");
        Pair<?, ?> two = new Pair<>("Foo", 2);
        System.out.println(one.getClass() == Pair.class);
        System.out.println("are run-time types of one and two the same? : " + (one.getClass() == two.getClass()));
    }
}
