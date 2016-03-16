package tmp;

import java.util.function.BinaryOperator;

/**
 * <p> I was a little intrigued by the {@linkplain java.util.stream.Stream#reduce(BinaryOperator)}
 * description given in the Javadoc: </p>
 * <pre>
 *     boolean foundAny = false;
 *     T result = null;
 *     for (T element : this stream) {
 *         if (!foundAny) {
 *             foundAny = true;
 *             result = element;
 *         }
 *         else
 *             result = accumulator.apply(result, element);
 *     }
 *     return foundAny ? Optional.of(result) : Optional.empty();
 * </pre>
 * Can the following work just as well?
 * <pre>
 *     for (T element : this stream) {
 *         return accumulator.apply(result,
 *     }
 * </pre>
 * Created by kmhaswade on 3/12/16.
 */
public class ReduceEx {
}
