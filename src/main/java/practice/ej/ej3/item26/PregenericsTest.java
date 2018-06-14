package practice.ej.ej3.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class PregenericsTest {
    public static void main(String[] args) {
//        final Collection stamps = new ArrayList(10);
        final Collection<Stamp> stamps = new ArrayList<>(10);
//        stamps.add(new Coin(NICKEL));
        for (Iterator i = stamps.iterator(); i.hasNext();) {
            Stamp stamp = (Stamp) i.next(); // Throws ClassCastException
            stamp.cancel();
        }
    }

    enum ModernUsCoin {
        PENNY,
        NICKEL,
        DIME,
        QUARTER
    }

    private static final class Coin {
        private final ModernUsCoin value;

        Coin(ModernUsCoin value) {
            requireNonNull(value);
            this.value = value;
        }
    }

    private static final class Stamp {
        void cancel() {
            System.out.println("canceling this stamp!");
        }
    }
}
