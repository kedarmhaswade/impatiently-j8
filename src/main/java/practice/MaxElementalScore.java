package practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Given an English word and the periodic table, find its "maximum elemental score".
 * </p>
 * <p>
 * The elemental score of a word
 * is defined as the sum of atomic numbers of the elements whose symbols make up that word by
 * concatenation.
 * </p>
 * <p>
 * In case there are multiple such arrangements, maximum sum determines the maximum elemental
 * score. If no such arrangement is possible, the maximum elemental score of that word is -1.
 * Print all the possible arrangements elementally, e.g. the word <code>herbal</code> should be
 * printed as <code>HErBAl</code>, the word <code>unknown</code> should be printed as
 * <code>UNKNoWN</code>.
 * </p>
 * Created by kedar on 2/18/17.
 */
public class MaxElementalScore {
    static final Map<String, Element> table = new HashMap<>(118);
    static {
        try {
            readPeriodicTable("/Users/kedar/gh/impatiently-j8/elements.txt", table);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static class Element {
        static final Element NONE = new Element("", "", 0);
        final String name, symbol;
        final int ano;

        Element(String name, String symbol, int ano) {
            this.name = name;
            this.symbol = symbol;
            this.ano = ano;
        }

        static Element valueOf(String csl) { // csl = comma-separated line
            String[] parts = csl.split("\\s*,\\s*");
            return new Element(parts[0], parts[1], Integer.valueOf(parts[2]));
        }

        @Override
        public String toString() {
            return "n: " + name + ", s: " + symbol + ", a: " + ano;
        }
//        @Override
//        public boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Element) {
//                Element that = (Element)o;
//                return this.symbol.equalsIgnoreCase(that.symbol); // all that matters is symbol
//            }
//            return false;
//        }
//        @Override
//        public int hashCode() {
//            return this.symbol.hashCode(); // all that matters is symbol
//        }
    }

    static class Node<T extends Element> {
        static final Node<Element> ROOT = new Node<>(Element.NONE, null, 0);
        final T e;
        final Node<T> parent;
        final List<Node<T>> children;
        final int offset;

        Node(T e, Node<T> parent, int offset) {
            this.e = e;
            this.parent = parent;
            this.offset = offset;
            children = new ArrayList<>(4);
        }

        int score() {
            if (this.parent == null) {
                return this.e.ano;
            }
            return this.parent.score() + this.e.ano;
        }

        private String rScoreString() { // recursive routine
            if (parent == null) {
                return "";
            }
            String p = parent.rScoreString();
            return (p.length() == 0 ? "" : p + " + ") + this.e.symbol + " = " + this.e.ano;
        }

        public String scoreString() {
            return this.rScoreString() + " :total = " + this.score();
        }

        @Override
        public String toString() {
            return this.e.symbol + ", " + this.offset + " -> " +
                (this.parent == null ? "null" : this.parent.toString());
        }
    }

    static int getMaxElementalScore(String word, Map<String, Element> table) {
        // assume that symbol lengths are either 1 or 2 or 3.
        Deque<Node<Element>> q = new LinkedList<>();
        List<Node<Element>> maxNodes = new ArrayList<>(2);
        int len = word.length();
        int max = -1;
        Node<Element> maxNode = null;
        q.add(Node.ROOT);
        while (!q.isEmpty()) {
            Node<Element> n = q.remove();
            int startIndex = n.offset;
            if (startIndex == len) { // we found the word represented in n
                maxNodes.add(n);
//                System.out.println(n.rScoreString() + " = " + n.score());
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (startIndex + i > len) {
                        break;
                    }
                    String ss = word.substring(startIndex, startIndex + i);
                    String key = ss.toLowerCase();
                    if (table.get(key) != null) {
                        Element e = table.get(key);
                        q.add(new Node<>(e, n, startIndex + i));
                    }
                }
            }
        }
        if (maxNodes.isEmpty()) {
            return -1;
        } else {
            max = Integer.MIN_VALUE;
            for (Node n : maxNodes) {
                max = Math.max(n.score(), max);
                System.out.println(n.scoreString());
            }
            return max;
        }
    }

    private static String get(Map<String, Element> table, String s, Comparator<String> cmp) {
        for (String key : table.keySet()) {
            if (cmp.compare(key, s) == 0) {
                return key;
            }
        }
        return null;
    }

    private static void readPeriodicTable(String file, Map<String, Element> eMap) throws IOException {
        Files.lines(Paths.get(file)).forEach(line -> {
            String[] parts = line.split("\\s*,\\s*");
            String symbol = parts[1];
            eMap.put(symbol.toLowerCase(), Element.valueOf(line)); // remember keys are lower-case
        });
    }

    public static void main(String[] args) throws IOException {
//        doDict();
        doWord("herbal");
        doTableItself();
    }
    static void doWord(String word) throws IOException {
        int max = getMaxElementalScore(word, table);
        if (max == -1) {
            System.out.println(word + " has no elemental score");
        } else {
            System.out.println("max elemental score is: " + max);
        }
    }
    static void doDict() throws IOException {
        int[] maxa = new int[1];
        String[] maxs = new String[1];
        Files.lines(Paths.get("/usr/share/dict/words")).forEach(line -> {
            String word = line.trim();
            int maxForWord;
            maxForWord = getMaxElementalScore(word, table);
            if (maxForWord == -1) {
//                System.out.println(word + " has no elemental score");
            } else {
                System.out.println("elemental score is: " + maxForWord);
            }
            if (maxForWord > maxa[0]) {
                maxa[0] = maxForWord;
                maxs[0] = word;
            }
        });
        System.out.println("Max of all, score: " + maxa[0] + ", and the word: " + maxs[0]);
    }
    static void doTableItself() {
        table.values().stream().forEach(e -> {
            int x = getMaxElementalScore(e.name, table);
            System.out.println(x);
        });
    }
}
