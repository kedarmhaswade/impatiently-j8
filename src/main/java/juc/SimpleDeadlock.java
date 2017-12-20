package juc;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleDeadlock {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(2);

        for (int i = 0; i < 2; ++i) { // create and start threads
            new Thread(new Worker(startSignal, doneSignal, account)).start();
        }

        startSignal.countDown();      // let all threads proceed
        doneSignal.await();           // wait for all to finish
    }

    private static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private final Account account;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal, Account account) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.account = account;
        }

        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        private void doWork() throws InterruptedException {
            account.credit(100);
            account.debit(50);
            System.out.println(account.amount + " in: " + Thread.currentThread());
        }
    }

    static class Account {

        public final Object lock1 = new Object();
        public final Object lock2 = new Object();
        private final AtomicInteger amount;

        Account(int amt) {
            this.amount = new AtomicInteger(amt);
        }

        public void credit(int a) throws InterruptedException {
            synchronized (lock1) {
                synchronized (lock2) {
                    BigInteger.probablePrime(1000, new Random(1));
                    amount.addAndGet(a);
                }
            }
        }

        public void debit(int a) throws InterruptedException {
            synchronized (lock2) {
                synchronized (lock1) {
                    BigInteger.probablePrime(1000, new Random(1));
                    amount.addAndGet(-a);
                }
            }
        }

        public int get() {
            return amount.get();
        }
    }
}
