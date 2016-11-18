package practice;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *     You are given a long file that contains records with student ID's (an alphanumeric
 *     string) and a score. You are to find the student who has the maximum average of his/her
 *     top k scores. If a student has fewer than k scores, you should ignore that student.
 * </p>
 * <p>
 *     Having to store all the scores of a student when only top-k are required seems
 *     wasteful. A min-heap is appropriate for storing the top-k scores.
 * </p>
 * Created by kedar on 11/16/16.
 */
public class TopKAverageScore {

    public static Map.Entry<String, Double> maxAvg(Stream<String> records, int k) {
        final Map<String, PriorityQueue<Long>> scores = new HashMap<>();
        records.forEach(r -> {
            String[] parts = r.split("\\s+");
            String id = parts[0];
            long score = Long.valueOf(parts[1]);
            PriorityQueue<Long> minHeap = scores.get(id);
            if (minHeap == null)
                scores.put(id, new PriorityQueue<>(k));
            minHeap = scores.get(id);
            assert minHeap != null;
            if (minHeap.size() < k) {
                boolean added = minHeap.add(score);
                assert added : "score: " + score + " could not be added";
            } else {
                Long root = minHeap.peek();
                if (root < score) {
                    minHeap.remove();
                    minHeap.add(score);
                }
            }
        });
        return findMaxAvgEntry(scores, k);
    }


     private static final class MutablePair {
        String id = null;
        Double avg = Double.MIN_VALUE;
    }
    private static Map.Entry<String, Double> findMaxAvgEntry(Map<String, PriorityQueue<Long>> scores,
                                                             int threshold) {
        final MutablePair highest = new MutablePair();
        scores.entrySet().forEach(e -> {
            PriorityQueue<Long> nScores = e.getValue();
            if (nScores.size() >= threshold) {
                double avg = nScores.stream()
                        .collect(Collectors.summarizingLong(Long::valueOf)).getAverage();
                if (avg > highest.avg) {
                    highest.avg = avg;
                    highest.id = e.getKey();
                }
            }
        });
        return new AbstractMap.SimpleEntry<>(highest.id, highest.avg);
    }
}
