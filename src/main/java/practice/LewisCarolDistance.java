package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 5/18/16.
 */
public class LewisCarolDistance {
    private static Set<String> dict;
    private static Set<String> visited;

    static {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("/usr/share/dict/words"))) {
                dict = br.lines().map(String::toLowerCase).collect(Collectors.toSet());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StringBuilder buf = new StringBuilder(100);
        int ld = getLewisCarolDistance(args[0].toLowerCase(), args[1].toLowerCase(), buf);
        if (ld != -1) {
            System.out.println("lcd: "+ ld);
        } else {
            System.out.println("words are not equivalent");
        }
    }

    private static final class Node {
        final String word;
        final Node parent;
        final int pos;

        // word and parent differ in exactly one character
        Node(String word, Node parent, int pos) {
            this.word = word;
            this.parent = parent;
            this.pos = pos; // i was created from parent by replacing this position
        }
        List<Node> children(String stoppingAt) {
            List<Node> allChildren = new ArrayList<>();
            outOfGettingAllChildren:
            for (int i = 0; i < word.length(); i++) {
                if (parent != null && i == this.pos)
                    continue;
                List<String> iIndexed = getChildren(this.word, i);
                for (String child : iIndexed) {
                    Node n = new Node(child, this, i);
                    boolean added = allChildren.add(n);
                    assert added;
                    if (child.equals(stoppingAt))
                        break outOfGettingAllChildren; // alert: optimization --we do not need to create subsequent nodes;
                }
            }
            return allChildren;
        }
        @Override
        public String toString() {
            return parent == null ? word : parent.toString()  + " -> " + word;
        }
        public int lcd() {
            Node tmp = this;
            int lcd = 0;
            while (tmp != null && tmp.parent != null) {
                lcd += 1;
                tmp = tmp.parent;
            }
            return lcd;
        }
    }

    private static int getLewisCarolDistance(String from, String to, StringBuilder buf) {
        if (from.equals(to)) {
            System.out.println(from + " -> " + to);
            return 0;
        }
        Deque<Node> curQ = new ArrayDeque<>();
        curQ.add(new Node(from, null, -1));
        boolean found = false;
        Node candidate = null;
        visited = new HashSet<>();
        while (!curQ.isEmpty()) {
            Node tmp = curQ.remove();
            if (to.equals(tmp.word)) {
                found = true;
                candidate = tmp;
                break;
            }
            curQ.addAll(tmp.children(to));
        }
        if (!found)
            return -1;
        else {
            assert candidate != null;
            System.out.println(candidate);
            return candidate.lcd();
        }
    }

    private static List<String> getChildren(String word, int pos) {
//        System.out.println("get children of: " + word + ", at position: " + pos);
        List<String> children = new ArrayList<>();
        if (pos >= 0 && pos < word.length()) {
            char[] chars = word.toCharArray();
            char c = word.charAt(pos);
            char t = 'a';
            // we resort to lower-case ASCII characters corresponding to the English Alphabet
            while (t <= 'z') {
                if (t != c) {
                    chars[pos] = t;
                    String candidate = new String(chars);
                    if (dict.contains(candidate) && !visited.contains(candidate)) {
                        children.add(candidate);
                        visited.add(candidate);
                    }
                }
                t += 1;
            }
        }
        return children;
    }
}
