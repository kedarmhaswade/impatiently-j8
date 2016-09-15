package practice;

import org.junit.Test;

import static practice.ArrayRotate.right;
import static practice.Utils.print;

/**
 * Created by kmhaswade on 9/6/16.
 */
public class ArrayRotateTest {

    @Test
    public void testRight() throws Exception {
        int[] a = new int[] {1, 4, 8, 10};
        right(a, 2);
        print(a);
    }
}