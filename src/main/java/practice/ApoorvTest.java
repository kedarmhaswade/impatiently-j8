package practice;

import java.util.Arrays;

public class ApoorvTest {
    public static void main(String[] args) {
        int[] a = {5, -1, 6, 8, 9, 1, 2, -1};
        for (int j = 1; j < a.length; j++) {
            int e = a[j];
            int k;
            for (k = j - 1; k >= 0 && e < a[k]; k--) {
                a[k + 1] = a[k];
            }
            a[k + 1] = e;
        }
        System.out.println(Arrays.toString(a));
    }

    private static void doSomething(int i) {
    }
}
