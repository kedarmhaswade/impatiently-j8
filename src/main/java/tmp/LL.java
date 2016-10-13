package tmp;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static java.util.Arrays.asList;

/**
 * Created by kedar on 10/11/16.
 */
public class LL {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        System.out.println(ll.peek());
        ll.addFirst(1);
        ll.addAll(asList(3, 4));
        System.out.println(ll);
        System.out.println(ll.peek());
    }
}
