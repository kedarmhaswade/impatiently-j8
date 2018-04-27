package omag.quiz;

import static java.lang.System.out;
// line n1
@FunctionalInterface
interface Something {
    void execute();
    default void speak() {
        out.println("Hello!");
    }
}
public class TryThis {
    public void speak() {
        out.println("Bonjour!");
    }
    public void go() {
        Something s = this::speak; // line n2
        s.execute();
    }
    public static void main(String[] args) {
        new TryThis().go();
    }
}
