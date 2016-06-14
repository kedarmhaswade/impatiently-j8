package util;

/**
 * Created by kmhaswade on 6/2/16.
 */
public class MyDebug {
    public static void print(char c) {
        if (debug())
            System.out.print(c);
    }
    public static void print(int i) {
        if (debug())
            System.out.print(i);
    }
    public static void print(Object o) {
        if (debug())
            System.out.print(o);
    }
    public static void println(char c) {
        if (debug())
            System.out.println(c);
    }
    public static void println(int i) {
        if (debug())
            System.out.println(i);
    }
    public static void println(Object o) {
        if (debug())
            System.out.println(o);
    }

    private static boolean debug() {
        return System.getenv("DEBUG") != null || System.getenv("Debug") != null || System.getenv("debug") != null
                || System.getProperty("DEBUG") != null || System.getProperty("Debug") != null || System.getProperty
                ("debug") != null;
    }
}
