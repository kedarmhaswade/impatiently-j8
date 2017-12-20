package practice.ej;

import static java.util.stream.Stream.of;

public class StreamOfArray {
    public static void main(String[] args) {
        int[] pi = {3, 1};
        of(pi).forEach(System.out::print);
    }
}
