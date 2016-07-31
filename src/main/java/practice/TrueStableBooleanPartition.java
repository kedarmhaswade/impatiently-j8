package practice;

/**
 <p>
    Given an array a of n objects with Boolean-valued keys, reorder the array so that the objects that have
    the key <code>false</code> appear first. The relative ordering of objects with key <code>true</code>
    may not change. Use O(1) additional space and O(n) time.
 </p>
 * Created by kmhaswade on 7/30/16.
 */
public class TrueStableBooleanPartition {
    static class Item {
        final boolean key;
        final Object data; // satellite data
        Item(boolean key, Object data) {
            this.key = key;
            this.data = data;
        }
    }
    static int partition(Item[] a) {
        int length = a.length;
        int ti = length - 1; // the index of the leftmost true
        for (int i = length - 1; i >= 0; i--) {
            if (a[ti].key) {
                ti -= 1;
                continue;
            }
            if (a[i].key) {
                swap(a, i, ti--);
            }
        }
        return ti + 1;
    }
    static void swap(Item[] a, int i, int j) {
        // no bounds check
        Item tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
