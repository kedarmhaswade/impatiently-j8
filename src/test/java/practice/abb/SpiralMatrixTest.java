package practice.abb;

import com.google.common.base.Charsets;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static practice.abb.SpiralMatrixSolution.*;
import static practice.abb.SpiralMatrixSolution.processInput;

/**
 * Created by kmhaswade on 9/25/16.
 */
public class SpiralMatrixTest {

    @Test
    public void rectMatrixTest() {
        // a simple matrix, let's start with a 2x3 matrix
        // 2, 3
        // 1, 2, 3
        // 4, 5, 6
        InputStream is = new ByteArrayInputStream("2, 3\n1, 2, 3\n4, 5, 6\n".getBytes(Charsets.UTF_8));
        List<List<String>>matrix = processInput(is);
        assertEquals(new ArrayList<>(asList("1", "2", "3")), new ArrayList<>(matrix.get(0)));
        assertEquals(new ArrayList<>(asList("4", "5", "6")), new ArrayList<>(matrix.get(1)));
        SpiralMatrix sm = new SpiralMatrix(matrix);
        List<String> expected = asList("1", "2", "3", "6", "5", "4");
        ListIterator<String> iter = expected.listIterator();
        sm.walk(s -> assertEquals(iter.next(), s));
    }
    @Test
    public void columnMatrixTest() {
        // a simple 5-column matrix
        // 5, 1
        // "a"
        // "5-column"
        // "matrix"
        // "is"
        // "here"
        InputStream is = new ByteArrayInputStream("5,1\na\n5-column\nmatrix\nis\nhere".getBytes(Charsets.UTF_8));
        List<List<String>>matrix = processInput(is);
        assertEquals(5L, matrix.size());
        assertEquals(1L, matrix.get(0).size());
        assertEquals(1L, matrix.get(1).size());
        assertEquals(1L, matrix.get(2).size());
        assertEquals(1L, matrix.get(3).size());
        assertEquals(1L, matrix.get(4).size());
        List<String> strings = asList("a", "5-column", "matrix", "is", "here");
        SpiralMatrix sm = new SpiralMatrix(matrix);
        List<String> expected = strings;
        ListIterator<String> iter = expected.listIterator();
        sm.walk(s -> assertEquals(iter.next(), s));
    }

    @Test
    public void squareMatrixTest() {
        // a 5x5 square matrix of first 25 natural numbers as strings

        InputStream is = new ByteArrayInputStream(("5,5\n" +
                "1, 2, 3, 4,5\n" +
                "6, 7, 8,  9, 10\n" +
                "11, 12, 13, 14, 15\n" +
                "16, 17, 18, 19, 20\n" +
                "21, 22, 23, 24, 25\n")
                .getBytes
                (Charsets
                .UTF_8));
        List<List<String>>matrix = processInput(is);
        assertEquals(5L, matrix.size());
        SpiralMatrix sm = new SpiralMatrix(matrix);
        List<String> expected = asList("1", "2", "3", "4", "5", "10", "15", "20", "25",
                "24", "23", "22", "21", "16", "11", "6", "7", "8", "9", "14", "19",
                "18", "17", "12", "13");
        ListIterator<String> iter = expected.listIterator();
        sm.walk(s -> assertEquals(iter.next(), s));

    }

}