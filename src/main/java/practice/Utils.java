package practice;

/**
 * Created by kmhaswade on 8/22/16.
 */
public class Utils {
    public static String toStringArray(int[] a1) {
        int length = a1.length;
        if (length == 0) {
            return "[]";
        }
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ;i++) {
            b.append(a1[i]);
            if (i == length - 1) {
                b.append("]");
                break;
            }
            b.append(", ");
        }
        return(b.toString());

    }
    public static void print(int[] a1) {
        System.out.println(toStringArray(a1));
    }
    public static String toStringArray(long[] a1) {
        int length = a1.length;
        if (length == 0) {
            return "[]";
        }
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ;i++) {
            b.append(a1[i]);
            if (i == length - 1) {
                b.append("]");
                break;
            }
            b.append(", ");
        }
        return(b.toString());
    }

    public static void print(long[] a1) {
        System.out.println(toStringArray(a1));
    }
    public static String toStringArray(int[][] a2) {
        int length = a2.length;
        if (length == 0) {
            return "[[]]";
        }
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ;i++) {
            b.append(toStringArray(a2[i]));
            if (i == length - 1) {
                b.append("]");
                break;
            }
            b.append(",\n");
        }
        return b.toString();
    }
    public static void print(int[][] a2) {
        System.out.println(toStringArray(a2));
    }
    public static String toStringArray(long[][] a2) {
        int length = a2.length;
        if (length == 0) {
            return "[[]]";
        }
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ;i++) {
            b.append(toStringArray(a2[i]));
            if (i == length - 1) {
                b.append("]\n");
                break;
            }
            b.append(",\n");
        }
        return b.toString();
    }
    public static void print(long[][] a2) {
        System.out.println(toStringArray(a2));
    }
}
