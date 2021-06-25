package tmp;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class TempRoutines {

    /**
     * Returns all permutations of the given list of items.
     * <p>
     * Given a list of items like <code>["a", "bc", "def"]</code>, the returned list will contain the lists:
     *     <ul>
     *         <li>["a", "bc", "def"]</li>
     *         <li>["bc", "a", "def"]</li>
     *         <li>["bc", "def", "a"]</li>
     *         <li>["a", "def", "bc"]</li>
     *         <li>["def", "a", "bc"]</li>
     *         <li>["def", "bc", "a"]</li>
     *     </ul>
     * </p>
     *
     * @param items list of items
     * @return a list of all permutations of items
     */
    private static List<List<String>> getAllPerms(List<String> items) {
        List<List<String>> newPerms = new ArrayList<>();
        if (items.size() == 0)
            return newPerms; // base: empty list
        if (items.size() == 1) {
            List<String> perm = new ArrayList<>();
            perm.add(items.get(0));
            newPerms.add(perm);
            return newPerms; // base: a singleton list with a singleton string
        }
        String f = items.get(0);
        List<List<String>> prevPerms = getAllPerms(items.subList(1, items.size()));
        for (List<String> perm : prevPerms) {
            List<List<String>> newPerms1 = arrangeFirst(f, perm);
            newPerms.addAll(newPerms1);
        }
        return newPerms;
    }

    /**
     * Returns new permutations from a given permutation by inserting a given string at all
     * possible indexes.
     *
     * @param f    given string e.g. "a"
     * @param perm given permutation, e.g. ["bc", "def"]
     * @return a list of new permutations, e.g. [["a", "bc", "def"], ["bc", "a", "def"], ["bc", "def", "a"]]
     */
    private static List<List<String>> arrangeFirst(String f, List<String> perm) {
        List<List<String>> newPerms = new ArrayList<>(perm.size() + 1);
        for (int i = 0; i <= perm.size(); i++) {
            List<String> newPerm = new ArrayList<>(perm.size() + 1);
            newPerm.addAll(perm.subList(0, i));
            newPerm.add(f);
            newPerm.addAll(perm.subList(i, perm.size()));
            newPerms.add(newPerm);
        }
        return newPerms;
    }



}
