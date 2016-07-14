package tmp;

import java.io.IOException;

/**
 * Created by kmhaswade on 7/6/16.
 */
public class ScriptStarter {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = new ProcessBuilder("/tmp/forever.sh").start();
//        Process p = new ProcessBuilder("/tmp/forever.sh").inheritIO().start();
        p.waitFor();
    }
}
