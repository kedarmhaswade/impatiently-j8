package tmp;

/**
 * Created by kmhaswade on 5/9/16.
 */
public class Infinite {
    public static void main(String[] args) {
        int n = 10;
        float sum = 0;
        for (int i=1; i<=n; i++)
        {
            sum = sum + (float) 1/(i*i);
        }
        System.out.println(sum);
    }
}
