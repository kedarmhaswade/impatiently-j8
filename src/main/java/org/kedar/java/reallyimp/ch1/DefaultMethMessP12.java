package org.kedar.java.reallyimp.ch1;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Collection;

/**
 * <p>
 *     In the past, you were told that it's bad form to add methods to an interface because it would break existing code.
 *     Now you are told that it's okay to add new methods, provided you also supply a default implementation.
 *     How safe is that?
 * </p>
 * <p>
 *     Describe a scenario where the new stream method of the {@linkplain Collection} interface causes legacy code
 *     to fail compilation. What about binary compatibility? Will legacy code from a JAR file still run?
 * </p>
 * Created by kmhaswade on 2/21/16.
 */
@Ignore
public class DefaultMethMessP12 {

    /** The interface that compiles fine with Java SE 7 */
    /*
    static interface StreamingCollection<E> extends Collection<E> {
        public void stream();
    }
    */
    /*But since the new Collection interface has a default method with the same name but a different
     * return type, when we try to compile this interface (StreamingCollection) with Java SE 8 javac,
     * it fails to compile with this error as of JDK 1.8.0_74:
StreamingCollection.java:3: error: stream() in StreamingCollection clashes with stream() in Collection
    public void stream();
                ^
  return type void is not compatible with Stream<E#2>
  where E#1,E#2 are type-variables:
    E#1 extends Object declared in interface Collection
    E#2 extends Object declared in interface StreamingCollection
1 error
     */
    /* Thus, it is as safe as the names of the conflict of default method names is unlikely. If, however, a conflict
       of name occurs, the recompilation of existing/legacy classes/interfaces is going to fail.
     */

    /* Binary Compatibility */
    /* The JDK classes have to be backward compatible with those that are compiled with previous JDK classes.
       Thus, if you have a class like this:

import java.util.*;

import java.util.*;
class Main {
    static interface StreamingCollection<E> extends Collection<E> {
        public void stream();
    }
    static class StreamingList<E> extends ArrayList<E> implements StreamingCollection<E> {
        public void stream() {
            throw new RuntimeException("not yet implemented ;)");
        }
    }

    public static void main(String[] args) {
        try {
            StreamingList<Integer> ints = new StreamingList<>();
            ints.stream();
        } catch (RuntimeException r) {
            System.out.println("got the expected result out of StreamingCollection#stream");
        }
    }
}

       that compiles fine with Java SE 7 and runs fine with JRE 7 (producing the output: "got the expected result out of StreamingCollection#stream".

       Then, this class should run fine (without recompilation) when it is run with JRE 8 and produce identical
       output. Thus, the legacy classes do not have to change at all to run atop JRE 8, since binary
       compatibility is guaranteed.
     */
}
