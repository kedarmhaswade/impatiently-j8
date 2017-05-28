package tmp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by kedar on 5/16/17.
 */
public class ToStringLogger {
    private static final Logger logger = LogManager.getLogger(ToStringLogger.class);

    public static void main(String[] args) {
        List<String> names = getItFromSomewhere();
        long t1 = currentTimeMillis();
        logger.debug(names.toString());
        long t2 = currentTimeMillis();
        System.out.println("time spent: " + (t2 - t1) + " ms");
    }

    private static List<String> getItFromSomewhere() {
        List<String> names = new ArrayList<>();
        IntStream.range(0, 1_000_000)
            .mapToObj(i -> "name: " + i)
            .forEach(names::add); // ignore return
        return names;
    }
}
