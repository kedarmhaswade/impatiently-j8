
/**
 * Given an array of integers, rearrange the array so that all zeroes in the array are 
 * shifted to the end (but the order of the nonzero numbers is preserved). 
 * For instance, given the array <4, 0, 8, 0, 0, 4>, you would rearrange the array to be <4, 8, 4, 0, 0, 0>.
*/

import java.util.Arrays;

public class Rearrange {

  public static void rearrange(int[] a) {
    int nz = 0;
    for (int i = 0; i < a.length; i++) {
        if (a[i] == 0) {
            nz++;
        }
    }
    if (nz > 0) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                a[j] = a[i];
                j++;
            }
        }
        while (j < a.length) {
            a[j++] = 0;
        }
    }
  }

  public static void main(String... args) {
      int a[] = new int[] {4, 0, 8, 0, 0, 4};
      System.out.println(Arrays.toString(a));
      rearrange(a);
      System.out.println(Arrays.toString(a));
      a = new int[] {2, 3, 0, 1, 0, 1, 4, 0, 0};
      System.out.println(Arrays.toString(a));
      rearrange(a);
      System.out.println(Arrays.toString(a));
  }
}
