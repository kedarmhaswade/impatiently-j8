package util;

import java.util.Random;

/**
 * Created by kmhaswade on 6/2/16.
 */
public final class Utils {
    public static String randomString(int length, String alphabet, long seed) {
        StringBuilder sb = new StringBuilder(length);
        int nLetters = alphabet.length();
        Random r = new Random(seed);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(r.nextInt(nLetters)));
        }
        return sb.toString();
    }
}
