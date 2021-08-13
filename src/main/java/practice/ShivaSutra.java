package practice;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * An enum for Panini's ShivaSutraani. Each ShivaSutra is in a class (or enum) of its own! A ShivaSutra ends with a VIRAMA
 * which is recognized as an independent character by the Unicode consortium. In other words, a consonant like
 * ल् is not a single unicode code point, but two code points: 'ल', and '्' (the विराम character: 0x94D), even though
 * ल् is usually regarded as a single consonant in the Devanaagarii script.
 */
public enum ShivaSutra {
    ONE(1, "अइउण्"),
    TWO(2, "ऋऌक्"),
    THREE(3, "एओङ्"),
    FOUR(4, "ऐऔच्"),
    FIVE(5, "हयवरट्"),
    SIX(6, "लण्"),
    SEVEN(7, "ञमङणनम्"),
    EIGHT(8, "झभञ्"),
    NINE(9, "घढधष्"),
    TEN(10, "जबगडदश्"),
    ELEVEN(11, "खफछठथचटतव्"),
    TWELVE(12, "कपय्"),
    THIRTEEN(13, "शषसर्"),
    FOURTEEN(14, "हल्");

    private final int order;
    private final String mnemonic;
    /**
     * The index of the last character (this character is <em>not</em> covered by this {@linkplain ShivaSutra}).
     */
    private final int lastCharacterIndex;

    ShivaSutra(int order, String mnemonic) {
        this.order = order;
        this.mnemonic = mnemonic;
        this.lastCharacterIndex = mnemonic.length() - 2; // exclude the last char which is विरामः
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public int getOrder() {
        return order;
    }

    public char getLast() {
        return mnemonic.charAt(lastCharacterIndex);
    }

    public char getFirst() {
        return mnemonic.charAt(0);
    }

    public List<Character> getCoveredCharacters(char... startsWith) {
        int i = 0;
        if (startsWith.length != 0) {
            char ch = startsWith[0];
            i = mnemonic.indexOf(ch);
            if (i == -1) {
                throw new IllegalArgumentException("No such character " + ch + " in this शिवसूत्र " + mnemonic);
            }
        }
        List<Character> cs = new ArrayList<>(lastCharacterIndex);
        for (; i < lastCharacterIndex; i++) {
            cs.add(mnemonic.charAt(i));
        }
        return unmodifiableList(cs);
    }

    /**
     * Returns a list of this शिवसूत्र and शिवसूत्राणि that follow it up to and including <code>till</code>, if given. If
     * no <code>till</code> is provided, till = {@linkplain ShivaSutra#FOURTEEN}.
     *
     * @param till ShivaSutra that is optional.
     * @return an unmodifiable <code>List</code> of शिवसूत्राणि; the returned list has at least one शिवसूत्र. Never returns
     * <code>null or empty list</code>.
     */
    @Nonnull
    public List<ShivaSutra> following(ShivaSutra... till) {
        ShivaSutra last = null;
        if (till.length == 0) {
            last = FOURTEEN;
        } else {
            last = till[0];
        }
        List<ShivaSutra> f = new ArrayList<>(16);
        for (ShivaSutra sutra : ShivaSutra.values()) {
            if (sutra.order >= this.order && sutra.order <= last.order) {
                f.add(sutra);
            }
        }
        return unmodifiableList(f);
    }

    public boolean contains(char c) {
        return this.mnemonic.indexOf(c) != -1;
    }

    public int getLastCharacterIndex() {
        return lastCharacterIndex;
    }
}
