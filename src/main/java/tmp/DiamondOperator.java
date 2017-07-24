package tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kedar on 6/7/17.
 */
public class DiamondOperator {
    public static void main(String[] args) {
//        List<String> safe = new ArrayList<>(Arrays.asList(1, 2, 3)); // does not compile
        List<String> unsafe = new ArrayList(Arrays.asList(1, 2, 3));  // compiles and may fail at runtime
        String s = unsafe.get(1);
    }
}
