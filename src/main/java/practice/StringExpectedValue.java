package practice;

public class StringExpectedValue {

    private static double e(int stringLength) {
        int[] a = new int[stringLength + 1];
        int[] r = new int[a.length]; // array of remaining lengths
        r[0] = stringLength;
        long sum = r[0];
        for (int i = 1; i < r.length; i++) {
            r[i] = stringLength % i;
            sum += r[i];
        }
        return ((double) sum) / (stringLength + 1);
    }

    public static void main(String[] args) {
        System.out.println(e(100000000));
    }
}
