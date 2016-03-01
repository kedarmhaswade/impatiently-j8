package tmp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 2/24/16.
 */
public class FlatMapEx {
    static class TreeNode<T> {
        final List<TreeNode<T>> children;
        final T key;
        TreeNode(T key) {
            this.key = key;
            this.children = new ArrayList<>(4);
        }
        TreeNode<T> findFirst(T key) {
            return this.dft().findFirst().get();
        }
        TreeNode<T> add(T key) {
            TreeNode<T> child = new TreeNode<>(key);
            this.children.add(child);
            return child;
        }
        TreeNode<T> removeAll(T c) {
            this.children.removeIf((n) -> n.key.equals(c));
            return this;
        }
        Stream<TreeNode<T>> dft() {
            return Stream.of(this).flatMap((n) ->
                 n.children.isEmpty() ? Stream.of(this) : Stream.concat(childrenStream(), Stream.of(this)));
        }
        private Stream<TreeNode<T>> childrenStream() {
            return Stream.empty();
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(11);
        TreeNode<Integer> two1 = root.add(21);
        TreeNode<Integer> two2 = root.add(22);
        TreeNode<Integer> two3 = root.add(23);

        two1.add(211);
        two1.add(212).add(2121);

        two2.add(221);
        two2.add(222);

        two3.add(231);

        root.dft().forEach(n -> System.out.println(n.key));

    }
}
