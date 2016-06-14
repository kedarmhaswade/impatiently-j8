package tmp;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Trying to explain <a href="http://stackoverflow
 * .com/questions/36319606/converting-hexadecimal-to-base-64/36319662#36319662"> this </a> to myself.
 * Created by kmhaswade on 3/31/16.
 */
public class EncodingEx {
    public static void main(String[] args) {
        String hexEncoded = "a9";
        System.out.println(Arrays.equals(hexEncoded.getBytes(), hexEncoded.getBytes(StandardCharsets.UTF_8)));
        System.out.println(hexEncoded.getBytes(StandardCharsets.UTF_8).length);
        byte b = (byte)0b10101001;
        System.out.println(b);
        for (byte ab : hexEncoded.getBytes(StandardCharsets.UTF_8)) {
            System.out.println(ab + " = " + Integer.toBinaryString(ab));
        }
    }
}
