package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 *     A friend posed this problem in an interview.
 * </p>
 * <p>
 *     You are given a text file with lots of records in the following format: <br/>
 *     word-1 word-2,0.9 word-3,0.8 word-4,0.6
 *     word-2 word-5,0.9 word-3,0.5
 *     ...
 * </p>
 * <p>
 *     Note:
 *     <ul>
 *         <li> Every line starts with a word which we call a source-word. Each source-word is
 *              followed by a close-word and a closeness score s (0.0 < s < 1.0)
 *         </li>
 *         <li> All close-words for a source-word are sorted in the order of decreasing score.</li>
 *         <li> A close-word in a source-word list can be related to another source-word such
 *              the closeness score gets multiplied. For instance, in the above list, word-5 is
 *              not directly listed for word-1, but for word-2 which is listed for word-1. Therefore,
 *              the closeness score of word-5 w.r.t. word-1 is 0.9*0.9 = 0.81.</li>
 *         <li> In case a close-word is already encountered in a source-word list (direct or recursive)
 *              the first score is taken.</li>
 *     </ul>
 * </p>
 * <p>
 *     Given a word and a number k, you are to find the k closest close-words and list them in
 *     the order of decreasing closeness score.
 * </p>
 * Created by kedar on 5/26/17.
 */
public class ClosestKWords {

    public static class Pair {
        String word;
        double score;

        Pair(String word, double score) {
            this.word = word;
            this.score = score;
        }

        Pair(String word) {
            this(word, 1.0);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Pair) {
                Pair that = (Pair) o;
                return this.word.equals(that.word) && this.score == that.score;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 13 * this.word.hashCode() + 17 * Double.hashCode(this.score);
        }

        @Override
        public String toString() {
            return word + ": " + score;
        }

        public static Pair valueOf(String wordCommaScore) {
            String[] ws = wordCommaScore.split(",");
            return new Pair(ws[0], Double.valueOf(ws[1]));
        }

        static Comparator<Pair> INCR_SCORE_CMP = comparingDouble(p -> p.score); // break ties arbitrarily
    }

    static PriorityQueue<Pair> closestBfs(Map<String, List<Pair>> scores, String word, int k) {
        // implement BFS here
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(word)); // initialize with a Pair with given word and a dummy score of 1.0
        List<List<Pair>> sortedLists = getSortedLists(k, q, scores);
        // each of the sortedLists is reverse-sorted on scores, now use minHeap
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, Pair.INCR_SCORE_CMP);
        //TODO if the number of sorted lists < k, then we have a problem that needs to be fixed
        for (int i = 0; i < k; i++) {
            minHeap.add(sortedLists.get(i).get(0)); // add the zeroth element of each sorted list to the min-heap
        }
        for (List<Pair> sortedList : sortedLists) {
            for (int i = 1; i < sortedList.size(); i++) {
                Pair cur = sortedList.get(i);
                Pair root = minHeap.peek();
                if (cur.score > root.score) {
                    Pair removed = minHeap.remove();
                    assert root == removed;
                    boolean added = minHeap.add(cur);
                    assert added : "Strange! cur: " + cur + " could not be added to heap with root: " + root;
                } else {
                    break; // the lists are reverse sorted, all remaining scores are going to be lower
                }
            }
        }
        return minHeap;
    }

    private static List<List<Pair>> getSortedLists(int k, Queue<Pair> q, Map<String, List<Pair>> scores) {
        List<List<Pair>> sortedLists = new ArrayList<>(k);
        Set<String> seen = new HashSet<>(k);
        while (k > 0 && !q.isEmpty()) {
            Pair curPair = q.remove();
            String curWord = curPair.word;
            if (seen.contains(curWord))
                continue;
            List<Pair> curList = scores.get(curWord);
            if (curList == null)
                continue;
            curList = curList
                .stream()
                .filter(e -> !seen.contains(e.word)) // remove the ones that are already seen
                .map(p -> new Pair(p.word, p.score * curPair.score)) // adjust the scores
                .collect(toList());
            sortedLists.add(curList); // ignore return value!
            seen.add(curWord); // ignore return value!
            q.addAll(curList); // add the unseen children to the queue
            k--;
        }
        return sortedLists;
    }
}

// first attempt (wrong)
//public static PriorityQueue<Pair> closest(Map<String, List<Pair>> scores, String word, int k) {
//    PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, Pair.INCR_SCORE_CMP);
//    List<Pair> list = new ArrayList<>(scores.get(word)); // Queue makes is trickier and hence using a List
//    Set<String> seen = new HashSet<>(); // have I already seen this word?
//    seen.add(word); // avoid cycle
//    int readIndex = 0; // indicates the list element to read
//    int levelIndex = 0; // indicates the element whose direct close-words are to be fetched
//    while (k > 0) {
//        if (readIndex == list.size() - 1) {
//            Pair curPair = list.get(levelIndex);
//            list.addAll(scores.get(curPair.word)
//                .stream()
//                .map(p -> new Pair(p.word, p.score * curPair.score))
//                .filter(p -> !seen.contains(p.word))
//                .collect(toList()));
//            levelIndex++;
//        }
//        Pair e = list.get(readIndex);
//        if (!seen.contains(e.word)) {
//            boolean add = minHeap.add(e);
//            assert add;
//            seen.add(e.word);
//            readIndex++;
//            k--;
//        }
//    }
//    // now minHeap contains the top-k, as of now. We need to replace the root with a pair, if any, that has bigger score
//    Pair current = list.get(readIndex);
//    while (minHeap.peek().score < current.score) {
//        if (seen.contains(current.word))
//            continue;
//        minHeap.remove();
//        minHeap.add(current);
//        seen.add(current.word);
//        readIndex++;
//    }
//    return minHeap;
//}

