package tmp;

/**
 * Created by kedar on 4/2/17.
 */
public class StringAsPrimitive {
    public static void main(String[] args) {
        String s = "Hello";
        s = "रामः वनम् अगच्छत्।";
        printChars(s);
    }

    private static void printChars(String s) {
        System.out.println("length: " + s.length());
        s.codePoints().forEach(c -> System.out.println("char: " + Character.getName(c) + ", " + printCharFromCodepoint(c) + ", code point: " + c));
    }

    private static char printCharFromCodepoint(int cp) {
        char[] chars = Character.toChars(cp);
        for (char c : chars)
            return c; // this is the only character ;)
        throw new AssertionError("can't reach here");
    }
}
