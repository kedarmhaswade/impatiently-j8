package tmp;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by kmhaswade on 3/4/16.
 */
public class FileUtilsClientTest {

    @Test
    public void testAreSame() throws Exception {
        File f1 = new File("/tmp/f1");
        File f2 = new File("/tmp/f2");
        assertEquals(true, FileUtilsClient.areSame(f1, f2));
    }
}