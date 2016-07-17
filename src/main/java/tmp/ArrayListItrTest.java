package tmp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kmhaswade on 7/15/16.
 */
public class ArrayListItrTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(4);
        list.add(1);
        Iterator<Integer> itr = list.iterator();
        itr.next();
    }
}
