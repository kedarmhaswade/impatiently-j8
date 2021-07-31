package org.kedar.java.reallyimp.ch1;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 2/21/16.
 */
public class Ch1Test {
    @Test
    public void testNotUnitTestWay() {
        List<Class> ch1Classes = Arrays.asList(
                ComparatorThreadP1.class,
                ListerP3.class,
                ListerP4.class,
                ToLambdaP5.class,
                UncheckP6.class,
                ThisThenThatP7.class,
                EffectivelyFinalP8.class,
                ForEachIfP9.class,
                DefaultMethMessP12.class);
        org.kedar.java.reallyimp.ch1.MainTester.testMains(ch1Classes.stream().filter(c -> !c.isAnnotationPresent(Ignore.class)).collect(Collectors.toList()));
    }
}
