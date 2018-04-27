package tmp;

public class LambdaCallsite {
    public static void main(String[] args) {
        Runnable r1 = null;
        for (int i = 0; i < 2; i++) {
            Runnable r2 = System::gc;
            if (r1 == null) {
                r1 = r2;
            } else {
                System.out.println(r1 == r2 ? "shared" : "unshared");
            }
        }
        double d = 1.75;
        System.out.println("Fractional part: " + Double.toHexString(d - (int) d)
            + ", Integer part: " + Integer.toHexString((int) d));
    }
}
