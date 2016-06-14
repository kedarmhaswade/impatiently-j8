package tmp;

import java.io.IOException;

/**
 * Created by kmhaswade on 5/25/16.
 */
public class Foo {
    public static void main(String[] args) throws IOException {

//        try (BufferedReader reader = new BufferedReader(new FileReader("Foo.java"))) {
//            System.out.println(reader.readLine());
//        }
        System.out.println(areLSDSet(31, 5));
    }
    public static boolean areLSDSet(int n, int x) {
        // validate x, n
        int y = (1<<x) - 1;
        return (n & y) == y;
    }
    public static void test() {
        Boolean b = null;
        if (b) {
            System.out.println("oops");
        }
    }
}
