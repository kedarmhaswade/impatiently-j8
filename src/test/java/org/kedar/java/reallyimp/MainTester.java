package org.kedar.java.reallyimp.ch1;

import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by kmhaswade on 2/21/16.
 */
public class MainTester {

    public static void testMains(List<Class> mainClasses) {
//        mainClasses = mainClasses.stream().filter(c -> c.getAnnotation(Ignore.class) == null).collect(Collectors.toList());
        mainClasses.forEach(c -> {
            try {
                try {
                    Method main = c.getMethod("main", String[].class);
                    main.invoke(null, (Object) null);
                    System.out.println("main invoked on: " + c);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }
}