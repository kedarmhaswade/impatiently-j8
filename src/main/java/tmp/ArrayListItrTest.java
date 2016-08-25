package tmp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kmhaswade on 7/15/16.
 */
public class ArrayListItrTest {
    static volatile boolean removeNow = false;
    public static void main(String[] args) throws InterruptedException {
        try {
            noHasNext();
        } catch (Exception e) {
            // ignore
        }
        try {
            coMod();
        } catch (Exception e) {

        }
    }

    private static void noHasNext() {
        ArrayList<Integer> list = new ArrayList<>(4);
        Iterator<Integer> itr = list.iterator();
        itr.next(); // should throw NoSuchElementException at ArrayList.java:854
    }
    private static void coMod() {
        ArrayList<Integer> list = new ArrayList<>(4);
        list.add(1);
        list.add(2);
        Iterator<Integer> itr = list.iterator();
        list.remove(0); //structural modification after iterator creation
        if (itr.hasNext()) {
            System.out.println("wait, there's more!");
            itr.next();
        }
    }
}
