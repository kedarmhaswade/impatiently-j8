package tmp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kmhaswade on 7/15/16.
 */
public class ArrayListItrTest {
    static volatile boolean removeNow = false;
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>(4);
        list.add(1);
        Iterator<Integer> itr = list.iterator();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            itr.next();
        }).start();
        Thread.sleep(200);
        itr.next();
    }
}
