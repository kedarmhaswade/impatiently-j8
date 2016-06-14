package tmp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kmhaswade on 4/28/16.
 */
public class NonInterference {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Larry", "Moe", "Curly");
        strings.stream().forEach(s -> {
            if (s.length() < 4)
                strings.remove(s);
        });
    }
}
