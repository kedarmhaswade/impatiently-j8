package tmp;

import java.util.Objects;

/**
 * Created by kedar on 2/24/17.
 */
public class Main {

    public static void main(String[] args) {
        String s = "foo";
        System.out.println(String.class instanceof Class);  // statically
        System.out.println(Class.class.isInstance(String.class)); // dynamically
        System.out.println(s instanceof String);
        System.out.println(String.class.isInstance(s));
    }
}

class Dog {
    static String genus = "Cannes";

    String name;
}

class Cat {
    static String genus = "Felis";
    String name;
}