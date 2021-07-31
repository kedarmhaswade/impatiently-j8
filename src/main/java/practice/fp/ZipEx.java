package practice.fp;

import org.apache.commons.math3.util.Pair;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * In functional programming, one should focus on what, not how. Translating this to Java can be challenging.
 * <p>
 * Here's a problem: Consider that you have two generic lists (one of objects of type T1, another of objects of
 * Type T1). Now you want to "zip" these lists together such that a sequence of tuples is generated.
 * </p>
 * <p>
 * The technical name for this is
 * <a href="https://en.wikipedia.org/wiki/Convolution_(computer_science)">Convolution</a>:
 * a function which maps a tuple of sequences into a sequence of tuples.
 * </p>
 * <p>
 * What might such a "2-tuple zipping function" look like in Java? This example explores that.
 * </p>
 */
public class ZipEx {
    /**
     * A basic implementation of zipping. It does exactly what a zipper does, but assumes certain things on the
     * caller's part. It takes two sequences of items and returns a sequence of pairs of those items.
     *
     * @param first  a generic {@linkplain List} of items each of which is the "first item" in a {@linkplain Pair}
     * @param second a generic {@linkplain List} of items each of which is the "second item" in a {@linkplain Pair}
     * @param <U>    the type parameter of items in the first list
     * @param <V>    the type parameter of items in the second list
     * @return a {@linkplain List} of {@linkplain Pair} instances formed from those items. The returned list has as
     * many items as the smaller list of the two params. Never returns <code>null</code>.
     */
    public static <U, V> List<Pair<U, V>> pairs(@Nonnull List<U> first, @Nonnull List<V> second) {
        // a straightforward implementation follows
        /* basic: start */
        /*
        int limit = Math.min(first.size(), second.size());
        List<Pair<U, V>> pairs = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            pairs.add(new Pair<>(first.get(i), second.get(i)));
        }
        return pairs;
        */
        /* basic: end */
        // The idea is, however, to parameterize the behavior of the mapping function, i.e.
        // call zip3 with specific mapper which is the constructor of the Pair class
        return zip3(first, second, Pair::new);
        // the straightforward implementation is equivalent to the above one-line call to zip3, but it
        // is arguably much more expressive. This power of expression comes because of the definition
        // of the zip3 function below. zip3 enables callers to write code in a declarative manner -- the heart of
        // functional programming
    }

    /**
     * A Java implementation of the Haskell zip3 function.
     *
     * @param first  a generic {@linkplain List} of items of type U
     * @param second a generic {@linkplain List} of items of type V
     * @param mapper a {@linkplain BiFunction} that maps a parameter of type U and a parameter of type V to a result of
     *               type R
     * @param <U>    type of items in the first list
     * @param <V>    type of items in the second list
     * @param <R>    type of items in the returned list; also the type of the result of the mapper bi-function
     * @return a generic List of R; the returned list has as
     * many items as the smaller list of the two params. Never returns <code>null</code>.
     */
    public static <U, V, R> List<R> zip3(@Nonnull List<U> first, @Nonnull List<V> second, BiFunction<U, V, R> mapper) {
        int limit = Math.min(first.size(), second.size());
        List<R> ret = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            ret.add(mapper.apply(first.get(i), second.get(i)));
        }
        return ret;
    }
}
