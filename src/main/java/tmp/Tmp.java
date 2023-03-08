package tmp;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.List.of;

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
//        System.out.println(268435457 * 15);
//        Map<String, List<Integer>> map = new HashMap<>();
//        map.put("a", asList(1, 2, 3));
//        System.out.println(map);
//        System.out.println(Integer.parseInt("01"));
//        System.out.println("C is the first and the".replace(' ', '\0').length());
//        System.out.println("letter of this sentence".replace(' ', '\0').length());
//        Object l4 = asList(new String[]{"a", "b", "c"});
//        System.out.println("l4 " + l4);
//        System.out.println("String.class.isInstance(l4.get(0)) : " + String.class.isInstance(((List<String[]>) l4).get(0)));
//
//        List<String[]> l5 = new ArrayList<>();
//        l5.add(new String[]{"a", "b", "c"});
//        System.out.println("String[].class.isInstance(l5.get(0)) : " + String[].class.isInstance(l5.get(0)));
        arrayListDemo();
    }

    public static void arrayListDemo() {
        List<Integer> xs = new ArrayList<>(of(1, 2, 3));
        xs.add(4);
        System.out.println(xs);
//        System.out.println(xs.get(6));
//        System.out.println(xs.get(xs.size()-1));
//        System.out.println(xs.remove(xs.size()-1));

        // *while iterating*, non-structural modification is okay but not structural modification
        Iterator<Integer> iter = xs.iterator();
        int idx = 0;
        while (iter.hasNext()) {
            xs.set(idx, 111); // okay
            //xs.remove(idx); // throws CME
            System.out.println("iterated: " + iter.next());
        }
    }

}

