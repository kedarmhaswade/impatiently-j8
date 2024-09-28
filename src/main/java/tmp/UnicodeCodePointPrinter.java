package tmp;

import java.util.Arrays;
import java.util.stream.IntStream;

// Prints the Unicode code points of a given string.
public class UnicodeCodePointPrinter {

    static void print(String s) {
        int[] codePoints = s.codePoints().toArray();
        System.out.println(s + ": number of Unicode code points: " + codePoints.length);
        Arrays.stream(codePoints).forEach(i -> System.out.println("char: " + (char) i + ", code point dec: " + i + ", hex: " + Integer.toHexString(i)));
    }

    public static void main(String[] args) {
        print("!abc?");
        print("शक्ती");
        print("शकती");
    }
}
