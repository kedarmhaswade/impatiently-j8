package tmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *     run as: main-class rf pool-combo
 * </p>
 * Created by kmhaswade on 5/5/16.
 */
public class SampleFileGen {
    public static void main(String[] args) throws IOException {
        String rf = args[0];
        int pc = Integer.parseInt(args[1]);
        int sbc; //starting barcode
        if (args.length == 3)
            sbc = Integer.parseInt(args[2]);
        else
            sbc = 1;
        if (pc != 2 && pc != 3)
            throw new IllegalArgumentException("pool combo: 2 or 3");

        if (yes(rf, pc, sbc))
            gen(rf, pc, sbc);
        else
            System.out.println("Phew! did nothing ...");
    }

    private static void gen(String rf, int pc, int sbc) {
        String[] nfiles = (pc == 2) ? new String[]{"11", "12", "21", "22"} : new String[]{"111", "112", "121", "122",
                "211", "212", "221", "222"};
    }


    private static boolean yes(String rf, int pc, int sbc) throws IOException {
        System.out.println("run folder: " + rf + ", pool combo: " + pc + ", starting barcode: " + sbc + ", ok (Y/n)");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String line = br.readLine();
            return line.length() == 0 || line.equalsIgnoreCase("y");
        }
    }
}
