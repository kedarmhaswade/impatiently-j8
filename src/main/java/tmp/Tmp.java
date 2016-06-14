package tmp;

import java.util.Random;

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
    public static void main(String[] args) {
        System.out.printf("%5d $9.2f %52f%% %29s\n\n", 1, 2.3, 4.5,
                "fooo");
        Random r = new Random();
        System.out.println("" + Character.valueOf((char)(65 + r.nextInt(26))) + Character.valueOf((char)(65 + r.nextInt(26))));
    }
}
