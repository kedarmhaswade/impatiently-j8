package practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static practice.SalaryCap.analytical;

/**
 * Created by kedar on 10/23/16.
 */
public class SalaryCapTest {
    @Test
    public void analyticalTest1() throws Exception {
        int[] salaries = new int[] {90, 30, 100, 40, 20};
        int payroll = 210;
        assertEquals(60, analytical(salaries, payroll), 1e-4);
    }

}