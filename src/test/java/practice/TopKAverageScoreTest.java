package practice;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static practice.TopKAverageScore.maxAvg;

/**
 * Created by kedar on 11/18/16.
 */
public class TopKAverageScoreTest {
    @Test
    public void maxAvgTest() throws Exception {
        String[] scores = new String[] {
                "A 90",
                "B 90",
                "A 92",
                "A 94",
                "B 94",
                "C 96"
        };
        // clearly A has the best average (93) of two best scores
        Map.Entry<String, Double> max = maxAvg(Stream.of(scores), 2);
        assertEquals("A", max.getKey());
        assertEquals(93.0, max.getValue(), 1e-4);
        scores = new String[] {
                "A 90",
                "B 90",
                "A 92",
                "A 94",
                "B 94",
                "C 96",
                "C 94"
        };
        // now it is C.
        max = maxAvg(Stream.of(scores), 2);
        assertEquals("C", max.getKey());
        assertEquals(95.0, max.getValue(), 1e-4);
    }

}