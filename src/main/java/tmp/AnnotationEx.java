package tmp;

import org.junit.Ignore;
import org.kedar.java.reallyimp.ch1.DefaultMethMessP12;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kmhaswade on 2/22/16.
 */
public class AnnotationEx {
    public static void main(String[] args) {
        List<Class> c = Arrays.asList(DefaultMethMessP12.class);
        c.stream().filter(aClass -> aClass.isAnnotationPresent(Ignore.class)).forEach(System.out::println);
    }
}
