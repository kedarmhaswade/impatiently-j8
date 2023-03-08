package practice;

import java.util.*;

public class TreeSetDemo {

    public static void main(String[] args) {
        Set<Integer> ts = new TreeSet<>(Integer::compareTo);
        Random r = new Random(System.currentTimeMillis());
        int sz = 10;
        for (int i = 0; i < sz; i++) {
            ts.add(r.nextInt(100000));
        }
        Set<Integer> hs = new HashSet<>(ts);
        Iterator<Integer> tsi = ts.iterator();
        Iterator<Integer> hsi = hs.iterator();
        while(tsi.hasNext()) {
            int ti = tsi.next();
            int hi = hsi.next();
            //System.out.printf("ts: %d, hs: %d, hash-set order differs? %s%n", ti, hi, ti == hi ? "no" : "yes");
            if (ti != hi) {
                System.out.println("elements differ: ti = " + ti + ", hi = " + hi);
            }
        }
    }
}
