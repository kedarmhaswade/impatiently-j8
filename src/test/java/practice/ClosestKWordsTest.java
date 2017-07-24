package practice;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static practice.ClosestKWords.closestBfs;

/**
 * Created by kedar on 5/26/17.
 */
public class ClosestKWordsTest {
    @Test
    public void basicTest() throws Exception {
        String basic =
                "customer user,0.95 client,0.8 helper,0.7 bill,0.5\n" +
                "user ease-of-use,0.95 primary,0.8, adopter,0.75\n" +
                "client contract,0.9 payment,0.85\n" +
                "ease-of-use ux,0.9 ui,0.85";
        StringReader reader = new StringReader(basic);
        try(BufferedReader br = new BufferedReader(reader)) {
            Map<String, List<ClosestKWords.Pair>> scores = new HashMap<>();
            br.lines().forEach(line -> {
                String[] parts = line.split("\\s+");
                scores.put(parts[0],
                    stream(parts, 1, parts.length)
                    .map(ClosestKWords.Pair::valueOf)
                    .collect(toList()));
            });
            String word = "user";
            int k = 2;
            System.out.println(closestBfs(scores, word, k));
        }
    }

}