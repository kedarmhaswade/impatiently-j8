package tmp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * <p>
 *     Some regex experiments.
 * </p>
 * Created by kmhaswade on 8/17/16.
 */
public class RegexExp {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/strings"))) {
            String regex = br.readLine();
            String s;
            while ((s = br.readLine()) != null) {
                s = s.trim();
                System.out.println("string is: " + s + ", regex is: " + regex);
                System.out.println(Pattern.matches(regex, s));
            }
        }
        System.out.println("length of \\^ is: " + "\\^".length());
        String regex = "\\\\*";
        String s = "";
        System.out.println("string is: " + s + ", regex is: " + regex);
        System.out.println(Pattern.matches(regex, s));
    }
}
