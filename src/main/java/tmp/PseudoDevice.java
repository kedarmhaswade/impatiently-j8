package tmp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by kedar on 2/17/17.
 */
public class PseudoDevice {
    public static void main(String[] args) throws IOException {
        InputStream zis = new FileInputStream("/dev/urandom");
        byte[] buf = new byte[100];
        int br = zis.read(buf); // ignore return value!
        IntStream.range(0, buf.length).map(idx -> buf[idx]).forEach(System.out::println);
    }
}
