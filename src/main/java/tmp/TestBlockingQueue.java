package tmp;

import java.util.List;
import java.util.LinkedList;

class Producer implements Runnable{

    BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true)
        {
            try
            {
                blockingQueue.enqueue(counter++);
                Thread.sleep(400); // sleep for a while ;)
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{

    BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {


        while (true)
        {
            try
            {
                Object deq = blockingQueue.dequeue();
                Thread.sleep(1000); // sleeping to simulate using the de-queued item

            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

public class TestBlockingQueue {
    public static void main(String[] args) {

        BlockingQueue blockingQueue = new BlockingQueue(10);
        Thread producer = new Thread(new Producer(blockingQueue), "Prod");
        Thread consumer = new Thread(new Consumer(blockingQueue), "Cons");
        producer.start();
        consumer.start();
    }
}

class BlockingQueue {

    private List queue = new LinkedList();
    private int  limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }


    public synchronized void enqueue(Object item)
            throws InterruptedException {

        while (this.queue.size() == this.limit) {
            System.out.println("Wait Enque : "+Thread.currentThread().getName());
            wait();
        }

        System.out.println("Add Item : " + item.toString() + " " + Thread.currentThread().getName());
        this.queue.add(item);
        notifyAll();
    }


    public synchronized Object dequeue() throws InterruptedException {

        while (this.queue.size() == 0){
            System.out.println("Wait Deque : "+Thread.currentThread().getName());
            wait();
        }

        System.out.println("Remove Item : "  + this.queue.get(0) + " " + Thread.currentThread().getName());
        notifyAll();
        return this.queue.remove(0);
    }

}