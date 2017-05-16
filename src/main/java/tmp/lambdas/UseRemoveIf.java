package tmp.lambdas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

/**
 * <p>
 *     Java 8 introduces {@linkplain Collection#removeIf(Predicate)} which is demonstrated in this class.
 * </p>
 * Created by kedar on 5/15/17.
 */
public class UseRemoveIf {
    public static void main(String[] args) {
        List<String> names = asList("", "larry", null, "moe", null, "", "curly", null);
        List<String> stooges = new ArrayList<>(names);
        stooges.removeIf(e -> {
//            stooges.remove(e); // the abuse, would throw CME
            return e == null || e.length() == 0;
        });
        assert "larry".equals(stooges.get(0));
        assert "moe".equals(stooges.get(1));
        assert "curly".equals(stooges.get(2));
        assert stooges.size() == 3;
    }
}
