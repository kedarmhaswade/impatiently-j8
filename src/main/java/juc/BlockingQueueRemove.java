package juc;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kedar on 3/8/17.
 */
public class BlockingQueueRemove {

    public static void main(String[] args) {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(1);
        try {
            int head = q.remove();
            System.out.println(head);
        } catch (NoSuchElementException e) {
            System.out.println("queue is empty and the remove() call does not block, immediately throws: " + e);
        }
        try {
            int head = q.take(); // blocks, since queue is empty
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
