package tmp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;

/**
 * <p>
 *     An attempt to code up
 *     <a href="http://stackoverflow.com/questions/38274474/get-the-most-recent-file"> in Java 8</a>.
 * </p>
 * Created by kmhaswade on 7/8/16.
 */
public class MostRecentFile {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get(args[0]);
        String regex = args[1];
        Arrays.stream(dir.toFile().listFiles(fn -> fn.getName().matches(regex))).sorted((f1, f2) -> {
                    try {
                        FileTime c2= Files.readAttributes(f2.toPath(), BasicFileAttributes.class).creationTime();
                        FileTime c1= Files.readAttributes(f1.toPath(), BasicFileAttributes.class).creationTime();
                        return c2.compareTo(c1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            ).findFirst().ifPresent(f -> System.out.println("found: " + f.getName()));
    }
}
