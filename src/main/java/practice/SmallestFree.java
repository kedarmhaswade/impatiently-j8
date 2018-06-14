package practice;

import java.util.Arrays;

/**
 * Suppose you have a list of ID's: For example,
 * [8, 23, 9, 0, 12, 11, 1, 10, 13, 7, 41, 4, 14, 21, 5, 17, 3, 19, 2, 6]
 * How would you find the smallest number not in this list?
 */
public class SmallestFree {

    public static void main(String[] args) {
        int[] a = new int[] {8, 23, 9, 0, 12, 11, 1, 10, 13, 7, 41, 4, 14, 21, 5, 17, 3, 19, 2, 6};
        System.out.println(smallestFree(a, 0, a.length));
        a = new int[] {15, 1, 3, 4, 5, 10, 52, 25, 0, 2};
        System.out.println(smallestFree(a, 0, a.length));
        a = new int[] {0, 1, 2, 3};
        System.out.println(smallestFree(a, 0, a.length));
        a = new int[] {3, 2, 1, 10};
        System.out.println(smallestFree(a, 0, a.length));
    }

    static int smallestFree(int[] a, int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int pi = partitionIndex(a, lo, hi);
        System.out.println("pi: " + pi + ", a[pi]: " + a[pi] + ", lo: " + lo + ", hi: " + hi
            + ", a: " + Arrays.toString(a));
        if (pi == lo && pi < a[pi]) {
            return pi;
        }
        if (pi == a[pi]) {
            return smallestFree(a, pi + 1, hi);
        } else {
            return smallestFree(a, lo, pi);
        }
    }

    private static int partitionIndex(int[] a, int lo, int hi) {
        int pivot = a[lo];
        int i = lo;
        int j = i + 1;
        while (j < hi) {
            if (a[j] < pivot) {
                a[i] = a[j];
                a[j] = a[i + 1];
                i++;
            }
            j++;
        }
        a[i] = pivot;
        return i;
    }
}
