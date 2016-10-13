package practice;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kedar on 1/10/16.
 */
public class MultiplicationTest {
    @Test
    public void gradeSchool() throws Exception {
        assertEquals(50L, Multiplication.gradeSchool(10, 5));
        assertEquals(-1000L, Multiplication.gradeSchool(10, -100));
        assertEquals(1000L, Multiplication.gradeSchool(-10, -100));
    }

}