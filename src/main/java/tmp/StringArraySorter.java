package tmp;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 5/10/16.
 */
public class StringArraySorter {
    public static void main(String[] args) {
        String array[][] = {{"5", "22,2", "car payment", "visa", "21/04/2016"},
                {"1", "44,4", "shop", "cash", "16/02/2017"},
                {"2", "33,1", "shop", "cash", "15/01/2020"},
                {"3", "17,3", "gym", "visa", "10/01/2016"},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
        };

        Arrays.stream(array).filter(a -> a[2] != null)
                .sorted((o1, o2) -> Float.compare(Float.parseFloat(o1[1].replace(',', '.')), Float.parseFloat(o1[1].replace(',', '.'))))
                .forEach(a -> System.out.println("{" + Arrays.stream(a).map(s -> "\"" + s + "\"").collect(Collectors.joining(", ")) +
                "}"));
    }
}
