package tmp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by kmhaswade on 2/24/16.
 */
public class TreeWalkEx {
    public static void main(String[] args) throws IOException {
        System.out.println(Files.walk(Paths.get("."), 1).limit(10).max((a, b) -> a.compareTo(b)));
    }
}
