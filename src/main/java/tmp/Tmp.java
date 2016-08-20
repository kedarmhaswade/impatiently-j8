package tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kmhaswade on 3/31/16.
 */
public class Tmp {
    //    public static void main(String[] args) throws IOException {
//        URL u = new URL("http://localhost:8080");
//        URLConnection uc = u.openConnection();
//        uc.connect();
//        System.out.println(System.getProperty("java.net.preferIPv6Addresses"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//    }
//    public static void main(String[] args) {
////        System.out.printf("%5d $9.2f %52f%% %29s\n\n", 1, 2.3, 4.5,
////                "fooo");
////        Random r = new Random();
////        System.out.println("" + Character.valueOf((char)(65 + r.nextInt(26))) + Character.valueOf((char)(65 + r.nextInt(26))));
//    }
    public static void main(String[] args) {
        Object l4 = Arrays.asList(new String[]{"a", "b", "c"});
        System.out.println("l4 " + l4);
        System.out.println("String.class.isInstance(l4.get(0)) : " + String.class.isInstance(((List<String[]>) l4).get(0)));

        List<String[]> l5 = new ArrayList<>();
        l5.add(new String[]{"a", "b", "c"});
        System.out.println("String[].class.isInstance(l5.get(0)) : " + String[].class.isInstance(l5.get(0)));
    }
}

