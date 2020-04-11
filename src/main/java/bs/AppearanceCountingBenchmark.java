package bs;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 10)
public class AppearanceCountingBenchmark {
    public static final int LIM = 100_000_000;
    int[] a = new int[LIM]; // setup should fill this up
    int key = 100;

    @Setup
    public void initialize() {
        // create a large array, with some streaks
        for (int i = 0; i < 10; i++) {
            a[i] = 1;
        }
        for (int i = 10; i < LIM; i++) {
            a[i] = key;
        }
    }
    @Benchmark
    public int linear() {
        return AppearanceCounterInSortedSequence.countSlow(a, key);
    }
    @Benchmark
    public int bsRecursive() {
        return  AppearanceCounterInSortedSequence.countFastRecursive(a, key);
    }
    @Benchmark
    public int bsIterative() {
        return AppearanceCounterInSortedSequence.countFastIterative(a, key);
    }

    public static void main(String[] args) {
        AppearanceCountingBenchmark bm = new AppearanceCountingBenchmark();
        bm.initialize();
        int c1 = bm.linear();
        int c2 = bm.bsRecursive();
        int c3 = bm.bsIterative();
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
    }
}
