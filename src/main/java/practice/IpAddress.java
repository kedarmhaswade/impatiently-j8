package practice;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * <p>
 * Given a string without the intervening dots, determine all the valid IP4 addresses it can form.
 * </p>
 * Created by kmhaswade on 9/13/16.
 */
public class IpAddress {

    static List<String> validIp4s(String s) {
        if (s == null || s.isEmpty() || s.length() > 12)
            return emptyList();
        List<String> list = new ArrayList<>();
        rget("", s, 3, list);
        return list;
    }

    private static boolean isValidOctet(String s) {
        int length = s.length();
        if (s == null || length > 3)
            return false;
        Integer n = Integer.valueOf(s);
        boolean b = n >= 0 && n <= 255 && n.toString().length() == length;
//        if (! b)
//            System.out.println("octet: " + s + " is invalid");
        return b;
    }

    private static void rget(String prefix, String suffix, int ndots, List<String> acc) {
        if (suffix == null || suffix.isEmpty())
            return;
        if (ndots == 0) {
            if (isValidOctet(suffix))
                acc.add(prefix + suffix);
            return;
        }
        if (suffix.length() >= 1 &&  isValidOctet(suffix.substring(0, 1)))
            rget(prefix + suffix.substring(0, 1) + ".", suffix.substring(1), ndots - 1, acc);
        if (suffix.length() >= 2 && isValidOctet(suffix.substring(0, 2)))
            rget(prefix + suffix.substring(0, 2) + ".", suffix.substring(2), ndots - 1, acc);
        if (suffix.length() >= 3 && isValidOctet(suffix.substring(0, 3)))
            rget(prefix + suffix.substring(0, 3) + ".", suffix.substring(3), ndots - 1, acc);
    }
}
