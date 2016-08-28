package practice;

import org.junit.Test;

/**
 * Created by kmhaswade on 8/26/16.
 */
public class APlusBRootQGeneratorTest {

    @Test
    public void printTest() {
        APlusBRootQGenerator g = new APlusBRootQGenerator();
        g.get(20, System.out::println);
    }

}