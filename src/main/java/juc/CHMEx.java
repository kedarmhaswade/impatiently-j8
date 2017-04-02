package juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * <p>
 * Implementations of the {@link ConcurrentMap} must be thread-safe data structures. But not all
 * of its methods are thread-safe. For example, the {@linkplain ConcurrentMap#put(Object, Object)
 * put} method is not required to be atomic and two different threads may be updating the value for
 * the same key at the same time resulting in corruption.
 * </p>
 *
 * <p>
 * This example examines the {@link
 * ConcurrentHashMap}. The {@link ConcurrentMap#compute(Object, BiFunction)} method is atomic and
 * multiple threads can safely update the map. Several tasks that simulate finding a word in a
 * documenting and updating its frequency by random number. These tasks are submitted to an executor
 * and when all the tasks are done, an assertion is made about the expected frequency of the given
 * word.
 * </p>
 *
 * <p>
 * Created by kedar on 3/3/17.
 * </p>
 */
public class CHMEx {
    private static class WordCountUpdateTask implements Callable<Long> {

        private final Map<String, Long> map;
        private final String word;
        private static final Random r = new Random();
        private static final BiFunction<String, Long, Long> increment2 =
            (k, v) -> v == null ? 2 : v + 2;
        private static final BiFunction<String, Long, Long> incrementRandom =
            (k, v) -> v == null ? r.nextInt(100) : v + r.nextInt(100);

        WordCountUpdateTask(String word, Map<String, Long> map) {
            this.word = word;
            this.map = map;
        }

        @Override
        public Long call() {
//            return map.compute(word, increment2); // simulates finding the word twice
            return map.compute(word, incrementRandom); // simulates finding the word arbitrarily
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentMap<String, Long> map = new ConcurrentHashMap<>(1000);
//        Map<String, Long> map = new HashMap<>(1000); // this would be thread-unsafe
        // TODO: prefer CompletionService here
        ExecutorService exec = Executors.newCachedThreadPool(); // short-lived tasks
        String word = "foo";
        List<Future<Long>> futures = new ArrayList<>(500);
        for (int i = 0; i < 500; i++) {
            boolean added = futures.add(exec.submit(new WordCountUpdateTask(word, map)));
            assert added;
        }
        futures.stream()
            .mapToLong(f -> {
                try {
                    return f.get(); // blocks though some other futures may be ready, use completion service
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0;
                }
            })
//            .peek(System.out::println)
            .max()
            .ifPresent(m -> System.out.println("word count: " + map.get(word) + ", should be: " + m));
        exec.shutdown();
        exec.awaitTermination(2, TimeUnit.SECONDS);
    }
}
