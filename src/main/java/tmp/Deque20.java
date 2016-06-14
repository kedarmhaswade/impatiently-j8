package tmp;

/**
 * Created by kmhaswade on 5/20/16.
 */

import java.util.Arrays;
import java.util.Iterator;

public class Deque20<E> implements Iterable<E> {

    public final int INITIAL_SIZE = 16;

    private E[] items;
    private E[] temp;
    private int size;
    private int first;

    @SuppressWarnings("unchecked")  // Casting Object[] to E[]
    public Deque20() {
        items = (E[]) new Object[INITIAL_SIZE];
        size = 0;
        first = 0;
    }

    public void addFirst(E e) {
        if(size == items.length){
            doubleSize();
        }
        first -= 1;
        if (first == -1) {
            first = items.length - 1;
        }
        items[first] = e;
        size += 1;
    }

    public void addLast(E e) {
        if (size == items.length) {
            doubleSize();
        }
        items[(first + size) % items.length] = e;
        size++;
    }

    public void removeFirst() {
        items[first] = null;
        first++;
        size--;
    }

    public void removeLast() {
        items[((first + size) % items.length) - 1] = null;
        size--;
    }

    public E peekFirst() {
        if (size == 1) {
            return items[first];
        } else {
            return items[first];
        }
    }

    public E peekLast() {
        if (size == 1) {
            return items[first];
        } else {
            return items[((first + size) % items.length) - 1];
        }
    }

    @SuppressWarnings("unchecked")
    private void doubleSize() {
        temp = (E[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(first + i) % items.length];
        }
        items = temp;
        first = 0;
    }

    @SuppressWarnings("unchecked")
    private void realign() {
        temp = (E[]) new Object[size];
        for(int i = 0; i < size; i++){
            temp[i] = items[(first + i) % items.length];
        }
        items = temp;
        first = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentOffset = 0;

            @Override
            public boolean hasNext() {
                return currentOffset < size;
            }

            @Override
            public E next() {
                E n = items[(first + currentOffset) % items.length];
                currentOffset += 1;
                return n;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public String toString() {
        String str = "[";
        for (E e : items) {
            str += e + ",";
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        Deque20<Integer> deq = new Deque20<>();
        deq.addFirst(1);
        deq.addFirst(2);
        deq.removeFirst();
        System.out.println("first: " + deq.first);
        System.out.println(Arrays.toString(deq.items));
        deq.addLast(3);
        System.out.println(Arrays.toString(deq.items));
        for (Integer i : deq)
            System.out.println(i);
    }
}