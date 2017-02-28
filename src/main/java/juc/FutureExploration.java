package juc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * This class is purely an exploration of a simple {@link Callable} and {@link Future}.
 * It tries to explore what happens when the {@link Future#get()} is called, how exactly is
 * the caller blocked etc.
 * </p>
 * Created by kedar on 2/24/17.
 */
public class FutureExploration {

    public static void main(String[] args) {
        Callable<Long> filesizeTask = () -> {
            File f = Paths.get("/tmp", "foobar123").toFile(); // make sure the file doesn't exist
            while (!f.exists()) {
                System.out.println("sleeping for a few seconds for the file: " +
                    f.getAbsolutePath() + " to get created ...");
                Thread.sleep(5000);
                if (Thread.currentThread().isInterrupted())
                    return 0L;
            }
            return Files.lines(f.toPath()).mapToLong(String::length).sum();
        };
        // This task is a short-lived (hopefully), mostly waiting on something to happen,
        // so use CachedThreadPool
        ExecutorService exec = Executors.newCachedThreadPool();
        try {
            long size = exec.submit(filesizeTask).get(4_000, TimeUnit.MILLISECONDS); // this current thread (main) is now blocked
            // take thread dump at this point and see what's happening and then debug the flow
            System.out.println("size of the file: " + size + " bytes");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
