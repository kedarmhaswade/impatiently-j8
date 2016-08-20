package practice;

import static java.lang.System.arraycopy;

/**
 * <p>
 *     Finds out the number of inversions in a given array.
 * </p>
 * <p>
 *     There's of course an O(n^2) algorithm that examines each pair a[i, j] such that i &lt; j and a[i] > a[j].
 *     But if the original array can be mutated and we have access to additional memory, the running time can be
 *     improved. The idea is to use merge-sort like (divide and conquer) technique and count the inversions as we do
 *     that.
 * </p>
 * Created by kmhaswade on 8/19/16.
 */
public class Inversions {

    static int count(int[] a) {
        return count(a, 0, a.length);
    }
    static int count(int[] a, int lowInc, int highExc) {
        if (lowInc >= highExc - 1) {
            return 0;
        }
        int mid = (lowInc + highExc) >>> 1;
//        System.out.println(lowInc + ", " + highExc + ", " + mid);
        return    count(a, lowInc, mid)
                + count(a, mid, highExc)
                + mergeAndCount(a, lowInc, mid, highExc);
    }

    private static int mergeAndCount(int[] a, int lowInc, int mid, int highExc) {
        int len = highExc - lowInc;
        int[] b = new int[len];
        int i = lowInc, j = mid;
        int k = 0;
        int ninv = 0;
        while (i < mid && j < highExc) {
            if (a[i] <= a[j]) { // next in order element comes from the left array
                // no inversions, safely proceed in left array
                b[k++] = a[i++];
            } else { // next in order element comes from the right array
                //  a[i], a[i+1], ..., a[mid-1] all have inversions with a[j]
                ninv += (mid - i);
                b[k++] = a[j++];
            }
        }
        if (i == mid) {
            int surplusRight = highExc - j;
            if (surplusRight > 0)
                arraycopy(a, j, b, k, surplusRight);
        }
        if (j == highExc) {
            int surplusLeft = mid - i;
            if (surplusLeft > 0)
                arraycopy(a, i, b, k, surplusLeft);
        }
        // another O(n) arraycopy operation!
        arraycopy(b, 0, a, lowInc, len);
        return ninv;
    }
    static void print(int[] a) {
        int length = a.length;
        if (length == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ;i++) {
            b.append(a[i]);
            if (i == length - 1) {
                b.append("]");
                break;
            }
            b.append(", ");
        }
        System.out.println(b.toString());
    }
}
