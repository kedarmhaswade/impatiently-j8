package practice.ej;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Created by kedar on 5/9/17.
 */
public class BadMix {
    public static void main(String[] args) {
        long t = currentTimeMillis();
        Date d = new Date(t);
        Timestamp ts = new Timestamp(t);
        System.out.println("date: " + d);
        System.out.println("timestamp: " + ts);
        System.out.println(ts.equals(d));
        System.out.println(d.equals(ts));
    }
}
