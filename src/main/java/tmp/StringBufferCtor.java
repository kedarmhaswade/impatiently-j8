package tmp;

import java.util.Random;

/**
 * Created by kmhaswade on 2/23/16.
 */
public class StringBufferCtor {

    private static Random rnd = new Random();

    public static void main(String[] args) {
        StringBuffer word;
        switch (rnd.nextInt(2)) {
            case 1:
                // this ctor is not what we want -- there is an implicit widening!
                word = new StringBuffer('P');
                break;
            case 2:
                word = new StringBuffer('G');
                break;
            default:
                word = new StringBuffer('M');
        }
        word.append("ain");
        System.out.println(word);
    }
}
