package tmp;

/**
 * Created by kmhaswade on 4/14/16.
 */
public class Replacer {
    static String replace(String s, String[] values) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c != '?')
                sb.append(c);
            else
                sb.append(values[j++]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(replace("XYZ(?, ?, ?)", new String[]{"1", "Question?", "0.5"}));
        System.out.println(replace("XYZ(?, ?, ?)", new String[]{"foo", "bar", "baz"}));
    }
}
