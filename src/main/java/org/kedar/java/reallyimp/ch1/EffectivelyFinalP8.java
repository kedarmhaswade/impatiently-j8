package org.kedar.java.reallyimp.ch1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     What happens when a lambda expression captures values in an enhanced for loop such as this one?
</p>
 <pre>
 String[] names = { "Peter", "Paul", "Mary" };
 List<Runnable> runners = new ArrayList&lt;>();
 for (String name : names)
 runners.add(() -> System.out.println(name));
 </pre>
 <p>
    Is it legal? Does each lambda expression capture a different value, or do they
    all get the last value? What happens if you use a traditional loop:
    <code> for (int i = 0; i &lt; names.length; i++) </code>?
 * </p>
 * <p>
 *     Hmmm, I cheated. I wrote a program and then see that it works as if the name
 *     is effectively final every time. I am trying to find the reason ... todo
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class EffectivelyFinalP8 {
    public static void main(String[] args) {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));
        runners.forEach(Runnable::run);
        //reset
        runners = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            final String name = names[i];
            runners.add(() -> System.out.println(name));
        }
        runners.forEach(Runnable::run);
    }
}
