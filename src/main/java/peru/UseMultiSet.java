package peru;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by kmhaswade on 5/29/16.
 */
public class UseMultiset {
    public static void main(String[] args) {
        Multiset<Integer> mset = HashMultiset.create();
        mset.add(2);
        mset.add(1);
        mset.add(1);
        mset.add(1);
        mset.add(1);
        mset.add(3);
        mset.add(2);
        System.out.println("#1: " + mset.count(1) + ", size: " + mset.size());
        mset.forEach(e -> System.out.println(e));
    }
}
