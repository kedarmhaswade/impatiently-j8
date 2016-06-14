package juc;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/** A simple producer consumer demo using ArrayBlockingQueue.
 * Created by kmhaswade on 4/22/16.
 */
public class ProdCons {
    static final Random RANDOM = new Random();
    private static final class Producer implements Runnable {
        private final BlockingQueue<Item> q;

        Producer(BlockingQueue<Item> q) {
            this.q = q;
        }
        @Override
        public void run() {
            while (true)
                try {
                    Item e = new Item(RANDOM.nextInt(1000));
                    q.put(e);
                    System.out.println(Thread.currentThread().getId() + " produced: " + e.id);
                } catch (InterruptedException e) {
                    System.out.println("I: " + Thread.currentThread().getName() + " was interrupted!");
                    Thread.currentThread().interrupt();
                }
        }
    }
    private static final class InteractiveConsumer implements Runnable, Consumer<Item> {
        private final BlockingQueue<Item> q;

        InteractiveConsumer(BlockingQueue<Item> q) {
            this.q = q;
        }
        @Override
        public void run() {
            while (true)
                try {
                    System.out.println("Press a key to consume!");
                    System.in.read();
                    accept(q.take());
                } catch (InterruptedException e) {
                    System.out.println("I: " + Thread.currentThread().getName() + ", id: " + Thread.currentThread()
                            .getId() + " was interrupted!");
                    Thread.currentThread().interrupt();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        @Override
        public void accept(Item item) {
            System.out.println(Thread.currentThread().getId() + " consumed: " + item.id);
        }
    }

    private static final class Item {
        final long id;
        Item(long id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Item> q = new ArrayBlockingQueue<>(4);
        new Thread(new Producer(q), "producer").start();
        new Thread(new InteractiveConsumer(q), "consumer").start();
    }
}