package tmp;

/**
 * Created by kmhaswade on 6/2/16.
 */
public class LambdaScope {
    public void doWork() {
        Runnable runner1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(toString());
            }
        };
        new Thread(runner1).start();
    }
    @Override
    public String toString() {
        return "Outer class: " + this.getClass();
    }

    public static void main(String[] args) {
        LambdaScope ls = new LambdaScope();
        ls.doWork();
    }
}
