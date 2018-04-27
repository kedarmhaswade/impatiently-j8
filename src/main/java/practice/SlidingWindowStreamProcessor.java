package practice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>Apache Flink provides a sliding window (with length and slide, slide &lt; length)
 * for processing continuous stream. Here's the code that demonstrates my understanding of it.
 * </p>
 */
public class SlidingWindowStreamProcessor {

    private final int length;
    private final int slide;

    public SlidingWindowStreamProcessor(int length, int slide) {
        // slide > 0, length > 0
        if (slide >= length) {
            throw new IllegalArgumentException("slide: " + slide + " must be < than length: " + length);
        }
        this.length = length;
        this.slide = slide;
    }

    public <T> void process(Stream<T> stream) {
        Deque<Bucket<T>> buckets = new LinkedList<>();
        AtomicInteger itemSeq = new AtomicInteger(1);
        AtomicInteger bucketSeq = new AtomicInteger(0);
        buckets.addLast(new Bucket<>(bucketSeq.incrementAndGet()));
        stream.forEach(e -> {
            for (Bucket<T> bucket : buckets) {
                bucket.process(e);
            }
            int front = itemSeq.get();
            if ((front % slide) == 0) {
                buckets.addLast(new Bucket<>(bucketSeq.incrementAndGet()));
            }
            int back = front - length;
            if (back >= 0 && (back % slide) == 0) {
                Bucket<T> tBucket = buckets.removeFirst();
                System.out.println("Removed bucket: " + tBucket.id);
            }
            itemSeq.incrementAndGet();
        });
        System.out.println("End of Stream!");
    }

    public static void main(String[] args) {

        new SlidingWindowStreamProcessor(5, 3).process(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).boxed());
    }

    private static class Bucket<T> {
        private final int id;
        Bucket(int id) {
            this.id = id;
        }

        void process(T item) {
            System.out.println("bucket: " + id + ", item: " + item);
        }
    }
}
