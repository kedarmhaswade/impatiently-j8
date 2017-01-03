package tmp;

/**
 * Created by kedar on 12/26/16.
 */
public class TmpRegex {
    public static void main(String[] args) {
        for (String p : "a\\b".split("\\\\"))
            System.out.println(p);
    }
}
