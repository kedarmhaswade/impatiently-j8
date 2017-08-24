package practice.ej;

/** <p> EJ-2, enum with constant specific class bodies and data.</p>
 *
 */
public enum Operation {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/");

    private final String symbol;
    Operation(String symbol) { // note: private by design
        this.symbol = symbol; // no need to ensure "input validation"!
    }
    @Override public String toString() {
        return symbol;
    }

    public static void main(String[] args) {
        System.out.println(Operation.class);
    }
}
