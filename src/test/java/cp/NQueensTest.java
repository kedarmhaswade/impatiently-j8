package cp;

import org.junit.Assert;
import org.junit.Test;

public class NQueensTest {
    @Test
    public void s8() {
        Assert.assertEquals(92L, NQueens.numWays(8));
//        System.out.println(NQueens.numWays(15));
    }
}