package tmp;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by kmhaswade on 4/20/16.
 */
public class E {
    public static void main(String[] args) {

        BigDecimal one = new BigDecimal(1);
        BigDecimal e = new BigDecimal(1.0);
        BigDecimal divisor = new BigDecimal(1.0);

        for (int i =  1; i <= 12; i++) {

            divisor = divisor.multiply(new BigDecimal(i));
            System.out.println("divisor: " + divisor);
            e = e.add(one.divide(divisor, MathContext.DECIMAL32));

            System.out.println("e = " + e);

        }
    }

}
