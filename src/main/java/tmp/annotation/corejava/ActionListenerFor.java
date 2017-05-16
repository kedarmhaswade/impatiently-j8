package tmp.annotation.corejava;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by kedar on 4/2/17.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ActionListenerFor {
    String source();
}
