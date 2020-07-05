package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * A few algorithms about subsets.
 * </p>
 * Created by kedar on 10/7/16.
 */
public class Subsets {

    /**
     * An iterative algorithm to get the subsets of a given set. For an n-set, iterates through the
     * natural numbers from 0 to 2^n - 1 and looks at the bits that are set. Each set bit represents
     * a set member that would be chosen for inclusion in the subset that that number represents.
     *
     * @param set given Set of T
     * @param <T> type parameter
     * @return the PowerSet of the given set
     */
    public static <T> Set<Set<T>> getPowersetIteratively(Set<T> set) {
        int n = 1 << set.size(); // cardinality of the powerset,
        // should be long, but then there is no HashSet ctor that accepts a long!

        Set<Set<T>> ps = new HashSet<>(n);
        List<T> listView = new ArrayList<>(set); // the list view of the set for random access
        for (int i = 0; i < n; i++) {
            Set<T> subset = new HashSet<>();
            int j = i;
            int idx = 0;
            while (j != 0) {
                if ((j & 1) == 1) {
                    boolean added = subset.add(listView.get(idx));
                    assert added; // good to assert that set changes
                }
                idx += 1;
                j >>>= 1;
            }
            boolean ns = ps.add(subset);
            assert ns : "bug! subset: " + subset + " already added to the powerset";
        }
        return ps;
    }
}