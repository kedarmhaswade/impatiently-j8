package juc;

import java.sql.Time;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Example showing the use of {@linkplain java.util.concurrent.DelayQueue}.
 * In the example, the 'delayed' elements are produced quickly once every 10 seconds by a producer thread, but
 * are held in a DelayQueue such that every element waits at least for 20 seconds. The Queue is consumed
 * by a consumer thread. But the consumer blocks (because DelayQueue is a Blocking Queue) on removal of elements
 * from the queue at least till the delay has expired.
 *
 * Created by kmhaswade on 2/26/16.
 */
public class DelayEx {
    static final class DelayedMessage implements Delayed {
        private final String message;
        private final long createTime;
        DelayedMessage(String message) {
            this.message = message;
            this.createTime = System.currentTimeMillis();
        }

        public String get() {
            return message;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long holdTimeMillis = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS); // we hold for 10 seconds
            return unit.convert(System.currentTimeMillis() - createTime - holdTimeMillis, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
