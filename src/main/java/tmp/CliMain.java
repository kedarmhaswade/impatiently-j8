package tmp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kmhaswade on 5/26/16.
 */
public class CliMain {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("c", "count", true, "number of message to be generated");
        options.addOption("s", "size", true, "size of each messages in bytes");
        options.addOption("t", "threads", true, "number of threads");
        options.addOption("r", "is random", false, "is random");
        CommandLine cli = new DefaultParser().parse(options, args);

        int count = Integer.parseInt(cli.getOptionValue("c", "100"));
        int recordSize = Integer.parseInt(cli.getOptionValue("s", "512"));
        int threads = Integer.parseInt(cli.getOptionValue("t","4"));
        boolean isRandom = Boolean.valueOf(cli.getOptionValue("r", "true"));
        System.out.println(" threads "+threads);
        System.out.println(" count "+count);

        List<Integer> list = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
        List<Integer> copy = new ArrayList<>(list);
        copy.add(4);
    }
}
