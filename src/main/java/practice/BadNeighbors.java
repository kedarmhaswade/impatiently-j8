package practice;

/**
 * <p>
 *     Solution to the
 *     <a href = "https://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009">
 *         Bad Neighbors Topcoder problem</a>.
 * </p>
 * Created by kmhaswade on 4/25/16.
 */
public class BadNeighbors {

    public static void main(String[] args) {
        System.out.println(maxDonations(new int[]{ 10, 3, 2, 5, 7, 8 }));
        System.out.println(maxDonations(new int[]{ 11, 15 }));
        System.out.println(maxDonations(new int[]{ 7, 7, 7, 7, 7, 7, 7 }));
        System.out.println(maxDonations(new int[]{ 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }));
        System.out.println(maxDonations(new int[]{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
                6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
                52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 }));
    }
    private static int maxDonations(int[] donations) {
        int length = donations.length;
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            // we start at the ith house
            int[] total = new int[length]; // cumulative sum when we start collecting at a given house
            total[i] = donations[i];
            total[(i + 1) % length] = -1; //next-door neighbor banned
            total[(i - 1 + length) % length] = -1; // prev-door neighbor banned
            for (int j = (i + 1) % length; j != (i - 1 + length) % length; j = (j + 1) % length) {
                if (total[j] == -1) // skip neighbors
                    continue;
                int max = Integer.MIN_VALUE;
                for (int k = j - 2; k >= i; k--) {
                    int t = donations[j] + total[k];
                    if (t > max)
                        max = t;
                }
                total[j] = max;
            }
            int maxi = max(total);
            globalMax = maxi > globalMax ? maxi : globalMax;
//            System.out.println("i: " + i + ", globalMax: " + globalMax);
//            try {
//                System.in.read();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return globalMax;
    }


    private static int max(int[] total) {
        int m = total[0];
        for (int i = 1; i < total.length; i++)
            if (total[i] > m)
                m = total[i];
        return m;
    }
}
