package cp;

import org.junit.Assert;
import org.junit.Test;


public class FourQueensTest {

    @Test
    public void solve() {
        Assert.assertEquals(2L, FourQueens.solve());
    }
}