package tmp;

/**
 * Created by kmhaswade on 4/17/16.
 */
import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer {
    private final Timer timer;
    public CountDownTimer(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(seconds), 0, 1000);
    }

    class RemindTask extends TimerTask {
        private volatile int remainingTimeInSeconds;
        public RemindTask(int remainingTimeInSeconds) {
            this.remainingTimeInSeconds = remainingTimeInSeconds;
        }

        @Override
        public void run() {
            if (remainingTimeInSeconds != 0) {
                System.out.println(remainingTimeInSeconds + " ...");
                remainingTimeInSeconds -= 1;
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        CountDownTimer t = new CountDownTimer(5);
        System.out.println("Timer started");
        Thread.sleep(5000);
        t.end();
    }

    private void end() {
        this.timer.cancel();
    }
}