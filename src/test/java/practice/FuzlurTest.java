package practice;

import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class FuzlurTest {

    @Test
    public void evaluateSimplePostfix() {
        List<Object> expr = List.of(15, 8, 7, Operator.ADDITION, Operator.DIVISION);
        double result = Fuzlur.evaluatePostfix(expr);
        System.out.println(result + ", result == 1 ? " + (result == 1));
        expr = List.of(6, 1, 3, 4, Operator.DIVISION, Operator.SUBTRACTION, Operator.DIVISION);
        result = Fuzlur.evaluatePostfix(expr);
        System.out.println(result + ", result == 24 ? " + (result == 24));
    }
}