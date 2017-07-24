package exam;

/**
 * Created by kedar on 6/22/17.
 */
public class TwoDimArray {
    public static void main(String[] args) {
        String[][] x = new String[1][];
        x[0][0] = "Joe";
        System.out.println("I'm: " + x[0][0]);
    }
    // 1. Compilation fails at line 8
    // 2. Compilation fails at line 9
    // 3. An exception is thrown at line 9
    // 4. I'm Joe
    // 5. I'm null
    // Note: In Java, a 2-d array is an array of 1-d arrays, both the arrays need to be initialized.
}
