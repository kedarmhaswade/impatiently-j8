package tmp;

import java.util.OptionalInt;
import java.util.regex.Pattern;

/** http://stackoverflow.com/questions/36321610/how-to-test-if-u-is-contained-in-unicode-strings
 * Created by kmhaswade on 3/31/16.
 */
public class UnicodeEx {
    public static void main(String[] args) {
        char[] c1 = new char[]{'\'', '\u819c', '\u539a', '\u0061', 'a'};
        String[] strs = new String[]{new String(c1)};
        for (String s : strs) {
//           s.codePoints().forEach(System.out::println);
           s.codePoints().filter(c -> c > 4296).forEach(System.out::println);
        }
    }
}

//        String[] strs = new String[]{"\\u0030", "u'\u819c\u539a_act'", "u'wild\u5e45'", "u'Hello_world'", "u'hello'"};
//System.out.println(s);
//        int si = s.indexOf("\\");
//        if (si != -1) {
//        if (s.indexOf('u') == si + 1)
//        System.out.println("True");
//        else
//        System.out.println("False");
//        }
//        System.out.println("False");