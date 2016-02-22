package org.kedar.java.reallyimp.ch1;

import org.junit.Test;
import org.kedar.java.reallyimp.ch1.MainTester;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kmhaswade on 2/21/16.
 */
public class Ch1Test {
    @Test
    public void mainTest() {
        List<Class> ch1Classes = Arrays.asList(
                ComparatorThreadP1.class,
                ListerP3.class,
                ListerP4.class,
                ToLambdaP5.class,
                UncheckP6.class,
                ThisThenThatP7.class,
                EffectivelyFinalP8.class,
                ForEachIfP9.class);
        MainTester.testMains(ch1Classes);
    }
}
