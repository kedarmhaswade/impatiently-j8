package tmp;

import java.util.Random;

/**
 * Created by kedar on 2/24/17.
 */
public class Longing {
    static Long getEither() {
        Random r = new Random(System.currentTimeMillis());
        if (r.nextDouble() > 0.5)
            return Long.MAX_VALUE;
        else
            return 0L; // not 0!
    }
}
