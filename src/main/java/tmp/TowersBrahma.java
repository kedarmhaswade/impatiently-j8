package tmp;

/**
 * Created by kmhaswade on 7/27/16.
 */
public class TowersBrahma {

    public static void transfer(String src, String dest, String aux, int n) {
        if (n == 1) {
            System.out.println("transfer: " + src + "->" + dest);
        } else {
            transfer(src, aux, dest, n-1);
            System.out.println("transfer: " + src + "->" + dest);
            transfer(aux, dest, src, n-1);
        }
    }

    public static void main(String[] args) {
        transfer("A", "C", "B", 3);
    }
}
