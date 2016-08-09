package practice;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 *     The classic pegs and disks puzzle.
 * </p>
 * Created by kmhaswade on 7/27/16.
 */
public class TowersBrahma {

    public static void recur(String src, String dest, String aux, int n) {
        if (n >= 1) {
            recur(src, aux, dest, n-1);
            System.out.println("transfer: " + src + "->" + dest);
            recur(aux, dest, src, n-1);
        }
    }

    /**
     * <p>
     *     Uses the technique described in the paper
     *     <a href="http://bit.ly/recurse-iterate"> Recursion and Iteration</a> to solve the problem iteratively.
     *     The idea is to use a stack effectively.
     * </p>
     * @param src the source peg
     * @param dest the destination peg
     * @param aux the auxiliary peg
     * @param n number of disks
     */
    public static void iterate(String src, String dest, String aux, int n) {
        Deque<Object> stack = new ArrayDeque<>(n*4);
        boolean finished = false;
        while (!finished) {
            while (n >= 1) {
                // save the context
                stack.push(src);
                stack.push(dest);
                stack.push(aux);
                stack.push(n);
                // save the context
                n -= 1;
                String tmp = dest;
                dest = aux;
                aux = tmp;
            }
            if (stack.isEmpty()) {
                finished = true;
            } else {
                // retrieve the context
                n = (Integer) stack.pop();
                aux = (String) stack.pop();
                dest = (String) stack.pop();
                src = (String) stack.pop();
                // retrieve the context
                System.out.println("transfer: " + src + "->" + dest);
                n -= 1;
                // swap src and aux
                String tmp = src;
                src = aux;
                aux = tmp;
            }
        }
    }

    public static void main(String[] args) {
        recur("A", "C", "B", 4);
        System.out.println("------------");
        iterate("A", "C", "B", 4);
    }
}
