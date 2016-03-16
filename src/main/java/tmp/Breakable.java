package tmp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/** Given a dictionary and a string, determine if the string can be split into words from the dictionary.
 * Created by kmhaswade on 3/8/16.
 */
public class Breakable {
    private static final HashSet<String> dict = new HashSet<>(250_000);
    static {
        try {
            Files.lines(Paths.get("/usr", "share", "dict", "words"))
                        .filter(s -> s.length() >= 3)
                        .map(String::toLowerCase)
                        .filter(s -> s.matches("^[a-zA-Z0-9]*$"))
                        .forEach(s -> dict.add(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        if (isBreakable(words, "thiswasreallylong")) {
            System.out.println(words);
        } else {
            System.out.println("unbreakable");
        }
    }

    private static boolean isBreakable(List<String> words, String string) {
        if (dict.contains(string)) {
            words.add(string);
            return true;
        }
        for (int i = 2; i < string.length(); i++) {
            String candidate = string.substring(0, i);
            if (dict.contains(candidate)) {
                if (isBreakable(words, string.substring(i))) {
                    System.out.println(candidate);
                    words.add(candidate);
                    return true;
                }
            }
        }
        return false;
    }
}
