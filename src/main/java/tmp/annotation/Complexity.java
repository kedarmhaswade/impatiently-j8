package tmp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * <p>
 *     Examining annotations. See {@link java.lang.annotation.Target Target}.
 * </p>
 * Created by kedar on 4/2/17.
 */
@Target(METHOD)
@Retention(CLASS)
public @interface Complexity {
    enum BigOhNotation {
        O_1,
        O_LOGN,
        O_N,
        O_NLOGN,
        O_N2,
        O_N3,
        O_2N,
        O_NFACT
    }
    BigOhNotation time();
    BigOhNotation space();
}

