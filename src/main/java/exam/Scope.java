package exam;

/**
 * Created by kedar on 6/22/17.
 */
public class Scope {
    public static void main(String[] args) {
        // line n1
        for (int x = 0; x < 10; x++) {
            // line n2
        }
        // line n3
        // Which of the following are true?
        // 1. Inserting {int x = 100;} at line n1 results in compilation error
        // 2. Inserting int x = 100; at line n1 results in compilation error
        // 3. Inserting {int x = 100;} at line n2 results in compilation error
        // 4. Inserting int x = 100; at line n2 results in compilation error
        // 5. Inserting int x = 100; at line n3 results in compilation error
    }
}
