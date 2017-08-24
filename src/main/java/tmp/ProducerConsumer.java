package tmp;

//This is bad code ;). Only here for the SO answer
//
public class ProducerConsumer {

    public static int SAMPLE_INT = 0;

    public static void main(String[] args) {

        PC pc = new PC();

        Thread changer = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    pc.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread listener = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    pc.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        listener.start();
        changer.start();
    }
}

class PC {

    Object lock = new Object();

    public void producer() throws InterruptedException {
        synchronized(this){
            for (int i=0; i<5; i++){
                ProducerConsumer.SAMPLE_INT++;
                System.out.println("Changed value of int to: " + ProducerConsumer.SAMPLE_INT);
                wait();
                notify();
            }
        }
    }

    public void consumer() throws InterruptedException{
        synchronized(this){
            for (int i=0; i<5; i++){
                System.out.println("Receieved Change: " + ProducerConsumer.SAMPLE_INT);
                notify();
                wait();
            }
        }
    }
}