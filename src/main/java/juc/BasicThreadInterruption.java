package juc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * <p> <i>Tasks</i> are <i>canceled</i>, whereas <i> Threads</i> are <i>interrupted</i>. Tasks are a
 * high-level concept, whereas threads are low-level constructs. </p> <p> Generally speaking, we
 * should think in terms of task cancellations and not thread interruptions. But this program
 * explores the low-level details. In particular it tries to find out what {@link
 * Thread#interrupt()} actually does. </p> <p> Let us consider two threads: main and worker. The
 * main thread has a reference to the Thread object (that it names <code>worker</code>) that
 * represents the worker thread. As a result of that, it is possible for the main thread to
 * interrupt the worker thread by simply calling the {@linkplain Thread#interrupt()} method on it
 * as: <code>worker.interrupt()</code>. Note that this is same as main thread calling
 * <code>s.length()</code> when s is a reference to a String. The only (big) difference is that a
 * "Thread reference" represents an actual JVM thread running concurrently in the JVM/OS. <br/>
 * <br/>
 * When main thread calls the interrupt method, the code of the Thread#interrupt method runs in the
 * main thread. So, the main thread becomes the <i>calling thread</i>. What actually happens
 * in the interrupted thread (e.g. when main interrupts worker) is documented clearly in the specification of {@link
 * Thread#interrupt()}. </p>
 * <p>
 *     This class attempts to demonstrate what happens in various cases.
 * </p>
 * Created by kedar on 2/28/17.
 */
public class BasicThreadInterruption {
    public static void main(String[] args) {
        Thread worker = new Thread(new CooperativeWorker(), "cooperative-worker");
        interruptCooperativeThread(worker);
        worker = new Thread(new NoncooperativeWorker(), "non-cooperative-worker");
        interruptNoncooperativeThread(worker);
    }

    private static volatile boolean cooperativeThreadRunning = false;
    private static volatile boolean nonCooperativeThreadRunning = false;

    static class CooperativeWorker implements Runnable {
        final List<Integer> list = new LinkedList<>();
        @Override
        public void run() {
            cooperativeThreadRunning = true;
            synchronized (list) {
                while (list.isEmpty()) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Here is my interrupt status (should be false): " + Thread.currentThread().isInterrupted());
                        System.out.println(currentThread().getName() + " says: I am done");
                        break;
                    }
                }
            }
        }
    }

    static class NoncooperativeWorker implements Runnable {
        final List<Integer> list = new LinkedList<>();
        @Override
        public void run() {
            nonCooperativeThreadRunning = true;
            synchronized (list) {
                while (list.isEmpty()) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
//                        currentThread().interrupt(); // be a good citizen
                        System.out.println("Here is my interrupt status (should be false): " + Thread.currentThread().isInterrupted());
                        System.out.println(currentThread().getName() + " says: Thanks, but I am ignoring the interruption");
                        // no break!
                    }
                }
            }

        }
    }

    private static void interruptCooperativeThread(Thread worker) {
        worker.setDaemon(true);
        worker.start();
        while (! cooperativeThreadRunning) {
            try {
                System.out.println("sleeping for a sec ...");
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("interrupting my sleep ...");
                ie.printStackTrace();
            }
        }
        // interrupt several times?
        System.out.println(currentThread() + " says: I'm about to interrupt " + worker.getName());
        worker.interrupt();
        examineThreadState(worker);
    }

    private static void interruptNoncooperativeThread(Thread worker) {
        worker.setDaemon(true);
        worker.start();
        while (! nonCooperativeThreadRunning) {
            try {
                System.out.println("sleeping for a sec ...");
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("interrupting my sleep ...");
                ie.printStackTrace();
            }
        }
        // interrupt several times?
        System.out.println(currentThread() + " says: I'm about to interrupt " + worker.getName());
        worker.interrupt();
        examineThreadState(worker);
    }


     static void examineThreadState(Thread thread) {
        ScheduledExecutorService sched = Executors.newScheduledThreadPool(2);
        Callable<Boolean> task = () -> {
            if (thread.isAlive()) {
                System.out.println("thread: " + thread.getName() + " is still alive even after interruption was requested ...");
                return true;
            } else {
                System.out.println("yay! thread: " + thread.getName() + " responded to interrupt, stopped doing its work!");
                return false;
            }
        };
        // check thrice, at most, if previous attempts fail
        boolean alive = true; // assume it's alive
        int i = 0;
        while (alive && i++ < 3) {
            try {
                alive = sched.schedule(task, 1, TimeUnit.SECONDS).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (alive) {
            System.out.println("Thread: " + thread.getName() + " was still alive, even after requesting interruption ...");
        }
         sched.shutdown();
    }
}
