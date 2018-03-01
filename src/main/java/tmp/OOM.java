package tmp;

import java.util.Arrays;
import java.util.Map;

class OOM {
    public static void main(String... args) {
        //Thread hook = new Hook();
        //Runtime.getRuntime().addShutdownHook(hook);
        Pojo[] pojos = new Pojo[Integer.MAX_VALUE / 2];
        System.out.println(pojos);
    }

    private static final class Pojo {
        final int x;
        Pojo() {
            this.x = 1;
        }
        public String toString() {
            return "Pojo: " + this.x;
        }
    }

    private static class Hook extends Thread {
        @Override
        public void run() {
            Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
            stacks.forEach((t, sts) -> {
                System.out.println(t + ":");
                System.out.println(Arrays.toString(sts));
            });
        }
    }
}
