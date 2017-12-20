package juc;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * Finds the number of times the word <code>import</code> appears in this folder. A sequential
 * way to do this would be to open each file, count the number of times <code>import</code>
 * appears in it and then report the cumulative count.
 * </p>
 * <p>
 * Alternatively, we can have a file opened by a {@link java.util.concurrent.Callable} that
 * returns a {@link java.util.concurrent.Future} and then combine all the results inside
 * those <code>Future</code>s.
 * </p>
 * <p>
 * Of course, this is an IO intensive job, but whatever CPU intensive work happens after
 * the file has been read in OS buffers can be executed independently by the cores.
 * </p>
 * Created by kedar on 2/22/17.
 */
public class FutureEx {
    public static void main(String[] args) {
        String word = "import";
        String path = "/Users/kedar/gh/impatiently-j8/src/main/java/juc";
        System.out.println("In *all* the files in the path: " + path
            + ", number of times the word: " + word + " appears is: " + countWord(word, path));
    }

    private static long countWord(String word, String path) {
        File dir = new File(path);
        if (!dir.isDirectory() || !dir.canRead()) {
            throw new RuntimeException("not a directory or an unreadable directory: " + path);
        }
        File[] files = dir.listFiles((name) -> name.getName().endsWith(".java"));
        assert files != null;
        final List<Callable<Long>> tasks = new ArrayList<>(files.length);
        for (File f : files) {
            tasks.add(() -> {
                AtomicLong count = new AtomicLong();
                Files.lines(f.toPath()).forEach(line -> {
                    String[] words = line.split("\\s+");
                    for (String w : words) {
                        if (word.equals(w)) {
                            count.addAndGet(1);
                        }
                    }
                });
                return count.get();
            });
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        try {
            List<Future<Long>> results = exec.invokeAll(tasks);
            return results.stream().mapToLong(c -> {
                try {
                    return c.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0;
                }
            }).sum();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            exec.shutdown();
        }

    }
}
