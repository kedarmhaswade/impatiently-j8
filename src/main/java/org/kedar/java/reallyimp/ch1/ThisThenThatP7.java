package org.kedar.java.reallyimp.ch1;

/**
 * <p>
 * Write a static method andThen that takes as parameters two Runnable instances and returns
 * a Runnable that runs the first, then the second. In the main method, pass two lambda expressions
 * into a call to andThen, and run the returned instance.
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class ThisThenThatP7 {

    public static void main(String[] args) {
        int a = 1, b = 2;
        int c = a + b;
        // these two lambdas are called statement lambdas
        Runnable first = () -> System.out.println(a + " + " + b + " = ");
        Runnable second = () -> System.out.println(c);
        andThen(first, second).run();
    }

    static Runnable andThen(Runnable first, Runnable second) {
        // the lambda expression below captures first and second
        return () -> {
            first.run();
            second.run();
        };
    }
}
