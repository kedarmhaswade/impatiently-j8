package tmp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kedar on 3/2/17.
 */
public class RealTimeTransform {
    public static void main(String[] args) throws IOException {
        Map<Integer, StringBuilder> columns = new HashMap<>();
        readColumnsFromRows(columns, System.in);
        System.out.println(columns.values());
    }

    private static void readColumnsFromRows(Map<Integer, StringBuilder> columns, InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int rc = 0;
        String row;
        while ((row = reader.readLine()) != null) {
            if (rc == 0) {
                for (int i = 0; i < row.length(); i++) {
                    columns.put(i, new StringBuilder(10).append(row.charAt(i)));
                }
            } else {
                for (int i = 0; i < row.length(); i++) {
                    columns.get(i).append(row.charAt(i));
                }
            }
            rc += 1;
        }
    }
}
