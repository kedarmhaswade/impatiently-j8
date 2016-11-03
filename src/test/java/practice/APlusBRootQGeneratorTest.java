package practice;

import org.junit.Test;

import java.util.Iterator;

import static com.google.common.primitives.Doubles.asList;
import static org.junit.Assert.assertEquals;

/**
 * Created by kmhaswade on 8/26/16.
 */
public class APlusBRootQGeneratorTest {

    @Test
    public void basicTest() {
        double[] first20 = new double[] {
                0.0, 1.0, 1.4142, 2.0, 2.4142, 2.8284, 3.0, 3.4142, 3.8284, 4.0,
                4.2426, 4.4142, 4.8284, 5.0, 5.2426, 5.4142, 5.6568, 5.8284, 6.0, 6.2426
        };
        APlusBRootQGenerator g = new APlusBRootQGenerator();
        Iterator<Double> iter = asList(first20).iterator();
        g.get(20, d -> assertEquals(iter.next(), d, 1e-3));
    }

}