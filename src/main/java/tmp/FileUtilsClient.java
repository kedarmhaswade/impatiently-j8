package tmp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by kmhaswade on 3/4/16.
 */
public class FileUtilsClient {
    public static boolean areSame(File f1, File f2) {
        try {
            return FileUtils.contentEquals(f1, f2);
        } catch (IOException e) {
            return false; // squelch
        }
    }
}
