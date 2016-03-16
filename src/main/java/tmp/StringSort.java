package tmp;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 3/2/16.
 */
public class StringSort {
    public static void main(String[] args) {
        String s = args[0];
        char[] ca = s.toCharArray();
        Arrays.sort(ca);
        System.out.println(new String(ca));
//        System.out.println(new String(Arrays.sort(s.toCharArray())));
        Locale list[] = DateFormat.getAvailableLocales();
        Arrays.sort(list, (l1, l2) -> l1.getDisplayLanguage().compareTo(l2.getDisplayLanguage()));
        for (Locale aLocale : list) {
            System.out.println(aLocale.toString() + ": " + aLocale.getDisplayLanguage());
        }
    }
}
