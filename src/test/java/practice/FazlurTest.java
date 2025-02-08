package practice;

import org.junit.*;

import java.util.List;

public class FazlurTest {

    @Test
    public void evaluateSimplePostfix() {
        List<Object> expr = List.of(15, 8, 7, Operator.ADDITION, Operator.DIVISION);
        double result = Fazlur.evaluatePostfix(expr);
        System.out.println(result + ", result == 1 ? " + (result == 1));
        expr = List.of(6, 1, 3, 4, Operator.DIVISION, Operator.SUBTRACTION, Operator.DIVISION);
        result = Fazlur.evaluatePostfix(expr);
        System.out.println(result + ", result == 24 ? " + (result == 24));
    }
}