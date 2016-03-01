package tmp;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by kmhaswade on 2/28/16.
 */
public class PathsEx {
    public static void main(String[] args) {
        System.out.println(Paths.get(System.getProperty("user.dir")).toAbsolutePath());
        File propFile = new File("Property.properties");
        System.out.println(propFile.getAbsolutePath());
    }

}
