package tmp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 3/6/16.
 */
public class FileReadingEx {
    public static void main (String[] args) throws IOException {
        String fileName = "/tmp/sampleInput.txt";
        InputStream is = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.US_ASCII);
        BufferedReader br = new BufferedReader(isr);

        int lineNo = 1;
        String line;
        while ( (line = br.readLine()) != null) {
            System.out.println("Line " + lineNo + ": " + line);
            lineNo++;
        }
        br.close();
        System.out.println("Try again.");
        System.out.println();

        AtomicInteger lineNoAtomic = new AtomicInteger(1);
        try (Stream<String> linesStream = Files.lines(Paths.get(fileName), StandardCharsets.ISO_8859_1)) {
            linesStream.forEach(aLine -> {
                System.out.println("Line " + lineNoAtomic.get() + ": " + aLine);
                lineNoAtomic.incrementAndGet();
            });
        }
    }
}
