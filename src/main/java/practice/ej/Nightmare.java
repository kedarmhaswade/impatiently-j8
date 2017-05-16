package practice.ej;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.currentTimeMillis;

/**
 * Created by kedar on 5/9/17.
 */
public class Nightmare {
    private static final class Notorious implements Comparable<Notorious>{
        final int value;
        Notorious(int value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Notorious) {
                return this.value == ((Notorious)o).value;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return value; // isn't this really bad?
        }

        @Override
        public int compareTo(Notorious o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args) {
        int LIMIT = 50_000;
        long t1 = currentTimeMillis();
        Set<Notorious> set = new TreeSet<>();
        for (int i = 1; i <= LIMIT; i++) {
            boolean added = set.add(new Notorious(i));
            assert added;
//            System.out.println("added " + i);
        }
        System.out.println(set.contains(new Notorious(LIMIT)));
        long t2 = currentTimeMillis();
        System.out.println("It took: " + (t2 - t1) + " milliseconds");
    }
}
