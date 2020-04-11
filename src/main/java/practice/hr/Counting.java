package practice.hr;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static practice.hr.StdIoUtil.processTwo;

/**
 * Created by kedar on 1/10/16.
 */
public class Counting {
    public static void main(String[] args) throws IOException {
        int[] input = processTwo(System.in);
        int[] output = cSort(input);
        Arrays.stream(output).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
    static int[] cSort(int[] input) {
        int k = Arrays.stream(input).max().getAsInt() + 1;
        int[] count = new int[k];
        // countFastRecursive frequencies
        Arrays.stream(input).forEach(x -> count[x] += 1);

        // prefix sum
        int accIndex = 0;
        for (int i = 0; i < k; i++) {
            int freqI = count[i];
            count[i] = accIndex;
            accIndex += freqI;
        }
        // write it to output
        int length = input.length;
        int[] output = new int[length];
        for (int i = 0; i < length; i++) {
            output[count[input[i]]] = input[i];
            count[input[i]] += 1;
        }
        return output;
    }

}
