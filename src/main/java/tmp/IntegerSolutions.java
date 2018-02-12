package tmp;

/**
 * <p>An awesome math problem that should be solved using combinatorics. This is only to check :-)</p> <p>How many
 * integer solutions are there to x+y+z+w = 11 and 0<= x, y, z, w <= 4.</p>
 */
public class IntegerSolutions {
    public static void main(String[] args) {
        int n = 0;
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 4; y++) {
                for (int z = 0; z <= 4; z++) {
                    for (int w = 0; w <= 4; w++)
                        if (x + y + z + w == 11)
                            n += 1;
                }
            }
        }
        System.out.println(n);
    }
}
