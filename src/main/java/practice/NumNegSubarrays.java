package practice;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Solving <a href = "http://stackoverflow.com/questions/36988704/negative-subarrays-java-8">Number of negative
 * subarrays</a> of a given array. A negative subarray is a subarray whose elements sum up to a negative number.
 * Created by kmhaswade on 5/2/16.
 */
public class NumNegSubarrays {
    public static long get(int[] a) {
        long global = 0;
        for (int i = 0; i < a.length; i++) {
            long sum = 0, negs = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum < 0)
                    negs += 1;
//                System.out.println("num of negative subarrays start-end: [" + i + ", " + j +"] = " + negs);
            }
            global += negs;
        }
        return global;
    }
    public static long finallyGet(int[] a) {
        List<Integer> nums = IntStream.of(a).boxed().collect(Collectors.toList());
        return IntStream.range(0, nums.size())
                .flatMap(from -> IntStream.range(from + 1, nums.size() + 1)
                        .map(to -> nums.subList(from, to).stream()
                                .mapToInt(i -> i)
                                .sum()))
                .filter(sum -> sum < 0)
                .count();
    }
    public static void main(String[] args) {
        int[] a = getSomeArray(10_00);
        long t1 = System.currentTimeMillis();
        System.out.println("Java: " + get(a));
        System.out.println("time: " + (System.currentTimeMillis() - t1) + " ms");
        t1 = System.currentTimeMillis();
        System.out.println("Java 8?: " + finallyGet(a));
        System.out.println("time: " + (System.currentTimeMillis() - t1) + " ms");
    }

    private static int[] getSomeArray(int n) {
        Random r = new Random();
        int[] a = new int[n];
        for (int i = 0; i < n; i ++)
            a[i] = r.nextInt(20) * (r.nextBoolean() ? 1 : -1);
        return a;
    }
}
