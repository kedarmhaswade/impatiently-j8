package tmp;

public class ThisLeakPuzzle {
    private final int val;
    private final Printer printer;

    ThisLeakPuzzle(int val, Printer printer) {
        this.val = val;
        this.printer = printer;
        // local!
        Printer p = new Printer() {
            @Override
            public void print() {
                System.out.println(ThisLeakPuzzle.this.val);
            }
        };
    }

    ThisLeakPuzzle() {
        this(0, new Printer() {
            @Override
            public void print() {
            }
        });
    }

    public static void main(String[] args) {
        ThisLeakPuzzle p = new ThisLeakPuzzle();
    }
}
interface Printer {
    void print();
}
