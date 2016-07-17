package practice;

import java.util.List;

/**
 * Created by kmhaswade on 7/12/16.
 */
public class AlmostSorted {
    public enum Sorted {
        yes, no, swap, reverse;

        public String describe(List<Integer> indexes) {
            if (this == yes || this == no) {
                return name();
            } else {
                return name() + "\n" + indexes.get(0) + " " + indexes.get(1);
            }
        }
    }

    static Sorted isSorted(int[] a, List<Integer> indexes) {
        int inv1i = -1, inv2i = -1, ninv = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) {
                // we found an inversion
                ninv += 1;
                if (inv1i == -1)
                    inv1i = i - 1; // where first starts
                else
                    inv2i = i; // where the last one ends
            }
        }
        if (ninv == 0) {
          return Sorted.yes;
        } else if (ninv == 1) {
            if (a[inv1i + 1] > a[inv1i - 1] &&
                    (inv1i + 2 < a.length && a[inv1i] < a[inv1i + 2])) {
                indexes.add(inv1i + 1);
                indexes.add(inv1i + 2);
                return Sorted.swap;
            } else
                return Sorted.no;
        } else if (ninv == 2) {
            if ((((inv2i + 1) < a.length && a[inv1i] < a[inv2i + 1]) || (inv2i == a.length - 1)) &&
                    (a[inv2i] > a[inv1i - 1])) {
                indexes.add(inv1i + 1);
                indexes.add(inv2i + 1);
                return Sorted.swap;
            } else {
                return Sorted.no;
            }
        } else if (ninv == (inv2i - inv1i)) {
            // possible reverse
            if (((inv2i == a.length - 1) || (a[inv1i] < a[inv2i + 1]))
                    && (inv1i == 0 || (a[inv2i] > a[inv1i - 1]))) { // surely reverse
                indexes.add(inv1i + 1);
                indexes.add(inv2i + 1);
                return Sorted.reverse;
            } else {
                return Sorted.no;
            }
        } else {
            throw new RuntimeException("impossible!");
        }
    }
}
