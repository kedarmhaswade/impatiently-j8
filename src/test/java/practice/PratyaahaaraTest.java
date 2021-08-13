package practice;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static practice.ShivaSutra.ONE;
import static practice.ShivaSutra.THREE;

public class PratyaahaaraTest {

    private static void sout(Pratyaahaara pratyaahaara) {
        System.out.println(pratyaahaara);
    }

    @Test
    public void all() {
        List<Pratyaahaara> a = Pratyaahaara.all();
        System.out.println("all count: " + a.size());
        a.forEach(PratyaahaaraTest::sout);
    }

    @Test
    public void fromShivaSutre13() {
        List<Pratyaahaara> p13 = Pratyaahaara.FromShivaSutre(ONE, THREE);
//        p13.forEach(PratyaahaaraTest::sout);
        List<String> actNames = p13.stream().map(Pratyaahaara::name).collect(Collectors.toList());
        List<String> expNames = List.of("अङ्", "इङ्", "उङ्");
        assertEquals("wrong प्रत्यहाराः returned", expNames, actNames);
    }
}