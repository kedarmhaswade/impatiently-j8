package tmp;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by kmhaswade on 3/18/16.
 */
//@ThreadUnsafe
public class NonStreamingGenericPrimitiveDataRepo<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return theIterator.hasNext();
            }

            @Override
            public T next() {
                String next = theIterator.next();
                try {
                    Method m = theType.getDeclaredMethod("valueOf", String.class);
                    return (T) m.invoke(null, next.trim());
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    throw new RuntimeException("This is impossible!");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("data: " + next + " does not represent type: " + theType);
                }
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        throw new RuntimeException("left as an exercise :-) ");
    }
    private final ArrayList<String> theCache;
    private final Iterator<String> theIterator;
    private final Class<T> theType;
    public NonStreamingGenericPrimitiveDataRepo(Reader reader, Class<T> theType) throws IOException {
        Objects.requireNonNull(reader);
        Objects.requireNonNull(theType);
        if (Integer.class.equals(theType)
                || Long.class.equals(theType)
                || Float.class.equals(theType)
                || Double.class.equals(theType)
                || Boolean.class.equals(theType)
                || String.class.equals(theType)
                || Short.class.equals(theType)
                || Byte.class.equals(theType)) {
            theCache = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null)
                    theCache.add(line);
            }
            theIterator = theCache.iterator();
            this.theType = theType;
        } else {
            throw new IllegalArgumentException("Not a wrapper type: " + theType);
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i : new NonStreamingGenericPrimitiveDataRepo<>(ints(), Integer.class))
            System.out.println("read an int: " + i);
        for (float f : new NonStreamingGenericPrimitiveDataRepo<>(floats(), Float.class))
            System.out.println("read a float: " + f);
        for (boolean b: new NonStreamingGenericPrimitiveDataRepo<>(booleans(), Boolean.class))
            System.out.println("read a boolean: " + b);
    }
    static StringReader ints() {
        return new StringReader(" 1\n2\n-3\n4\n324\n2112221146\n");
    }
    static StringReader floats() {
        return new StringReader("1.0f\n3.25f\n-3.33f\n4.44f\n");
    }
    static StringReader booleans() {
        return new StringReader("false \ntrue\n");
    }
}
