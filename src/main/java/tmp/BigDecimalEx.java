package tmp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmhaswade on 2/27/16.
 */
public class BigDecimalEx {
    public static void main(String[] args) {
        List<BigDecimal> cs = new ArrayList<>();
        cs.add(new BigDecimal("4"));
        cs.add(new BigDecimal("-4"));
        cs.add(new BigDecimal("1"));
        BigDecimal a = new BigDecimal("3.1");
        System.out.println(evaluate(cs, a));
        System.out.println(evaluate(new double[]{4, -4, 1}, 3.1));
    }

    private static BigDecimal evaluate(List<BigDecimal> cs, BigDecimal a) {
        BigDecimal pa = BigDecimal.ONE;
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal c : cs) {
            result = result.add(c.multiply(pa));
            pa = pa.multiply(a);
        }
        return result;
    }
    private static double evaluate(double[] cs, double a) {
        double pa = 1.0;
        double result = 0.0;
        for (double c : cs) {
            result += c * pa;
            pa *= a;
        }
        return result;
    }
}
