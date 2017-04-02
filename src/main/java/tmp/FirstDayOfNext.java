package tmp;

import java.util.Calendar;

import static java.util.Calendar.*;
import static java.util.Calendar.DAY_OF_MONTH;

/**
 * Created by kedar on 3/24/17.
 */
public class FirstDayOfNext {
    public static void main(String[] args) {
        Calendar today = getInstance();
        today.clear();
        today.set(2016, DECEMBER, 31);
        System.out.println("today date: " + today.getTime());
        Calendar next = getInstance();
        next.clear();
        next.set(YEAR, today.get(YEAR));
        next.set(MONTH, today.get(MONTH) + 1);
//        next.set(DAY_OF_MONTH, 1);
        System.out.println("next  date: " + next.getTime());
    }
}
