package practice;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableList;

/**
 * <p>
 * Explores the प्रत्याहाराः based on {@linkplain practice.ShivaSutra शिवसूत्राणि}. Since everything is fixed by the fourteen
 * शिवसूत्राणि, प्रत्याहाराः are also fixed and, as such, they can themselves be enums, but we choose to run a program to deal with
 * them as listing them manually would be rather cumbersome. This class has some utilities to aid this exploration.
 * </p>
 * <p>
 * A प्रत्याहारः is formed by following these simple rules:
 * <ol>
 *     <li>
 *         A प्रत्याहारः has two characters (first, last). Their concatenation is the <i>name</i> of that प्रत्याहारः.
 *         A प्रत्याहारः is made from two शिवसूत्रे which may be the same.
 *     </li>
 *     <li>
 *         Choose a {@linkplain ShivaSutra शिवसूत्र} as its first शिवसूत्र. Choose another शिवसूत्र, whose
 *     {@linkplain ShivaSutra#order order} is not less than that of its first, as its last शिवसूत्र.
 *     </li>
 *     <li>
 *         The first character of a प्रत्याहारः is any letter except the last letter of the first शिवसूत्र that makes it. <br>
 *         The last character of a प्रत्याहारः is the last letter of the second शिवसूत्र that makes it. <br>
 *         The {@linkplain #getCoveredCharacters() characters covered} by a प्रत्याहारः is a concatenation of all the characters covered by its {@linkplain #first first
 *         शिवसूत्र} from the {@linkplain #startsWith starting character} and the covered characters by all the
 *         remaining शिवसूत्राणि up to the {@linkplain #last last शिवसूत्र} <b> in order</b>.
 *     </li>
 *     <li>
 *         Reject any प्रत्याहारः thus formed if it does not have any character that intervenes its first character
 *         and its second character. In other words, if a प्रत्याहारः can be formed by following above rules but it has
 *         a length of 2, then reject it. This is because of पाणिनि.
 *     </li>
 * </ol>
 * </p>
 */
public final class Pratyaahaara {
    private static final Character VIRAMA = '्';
    private final ShivaSutra first;
    private final ShivaSutra last;
    private final char startsWith;
    private final String name;
    private final List<Character> coveredCharacters;

    public Pratyaahaara(ShivaSutra first, ShivaSutra last, char startsWith) {
        if (first.getOrder() > last.getOrder()) { // .ordinal() may be used here
            throw new IllegalArgumentException("first ShivaSutra, order: " + first.getOrder() + ", must occur before last ShivaSutra order: " + last.getOrder());
        }
        if (!first.contains(startsWith)) {
            throw new IllegalArgumentException(first.name() + " does not contain: " + startsWith);
        }
        this.first = first;
        this.last = last;
        this.startsWith = startsWith;
        this.name = "" + startsWith + last.getLast() + VIRAMA;
        List<Character> t = new ArrayList<>(32);
        t.addAll(first.getCoveredCharacters(startsWith)); // may take some of the characters from first's mnemonic
        List<ShivaSutra> following = first.following(last);
        List<ShivaSutra> next = following.subList(1, following.size());
        for (ShivaSutra s : next) {
            t.addAll(s.getCoveredCharacters()); // must take all the characters from s's mnemonic
        }
        this.coveredCharacters = unmodifiableList(t);
    }

    public static List<Pratyaahaara> all() {
        // of course, everything could be cached!
        Set<Pratyaahaara> ps = new LinkedHashSet<>(256);
        ShivaSutra[] sss = ShivaSutra.values();
        int num = sss.length;
        for (int i = 0; i < num; i++) {
            for (int j = i; j < num; j++) {
                ps.addAll(FromShivaSutre(sss[i], sss[j]));
            }
        }
        return unmodifiableList(new ArrayList<>(ps));
    }
    public static List<Pratyaahaara> FromShivaSutre(ShivaSutra first, ShivaSutra last) {
        List<Pratyaahaara> ps = new ArrayList<>(32);
        for (int i = 0; i < first.getCoveredCharacters().size(); i++) {
            Pratyaahaara e = new Pratyaahaara(first, last, first.getMnemonic().charAt(i));
            if (e.isPanineya()) {
                ps.add(e);
            } else {
                // do not add as it is a single-character one
            }
        }
        return unmodifiableList(ps);
    }

    public ShivaSutra getFirst() {
        return first;
    }

    public ShivaSutra getLast() {
        return last;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the characters covered by this प्रत्याहारः.
     * @return
     */
    public List<Character> getCoveredCharacters() {
        return coveredCharacters;
    }

    /**
     * Panini does not recognize a one-character प्रत्याहारः.
     * @return true if the number of {@linkplain #getCoveredCharacters() covered characters} is two or more.
     */
    public boolean isPanineya() {
        return coveredCharacters.size() >= 2;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": " + coveredCharacters.toString();
    }
}
