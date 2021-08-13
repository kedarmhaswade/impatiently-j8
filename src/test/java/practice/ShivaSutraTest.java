package practice;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static practice.ShivaSutra.*;

public class ShivaSutraTest {

    @Test
    public void coveredCharsTest() {
        // full
        assertEquals("incorrect covered characters", List.of('अ', 'इ', 'उ'), ONE.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ऋ', 'ऌ'), TWO.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ए', 'ओ'), THREE.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ऐ', 'औ'), FOUR.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ह', 'य', 'व', 'र'), FIVE.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ल'), SIX.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ञ', 'म', 'ङ', 'ण', 'न'), SEVEN.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('झ', 'भ'), EIGHT.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('घ', 'ढ', 'ध'), NINE.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ज', 'ब', 'ग', 'ड', 'द'), TEN.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ख', 'फ', 'छ', 'ठ', 'थ', 'च', 'ट', 'त'), ELEVEN.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('क', 'प'), TWELVE.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('श', 'ष', 'स'), THIRTEEN.getCoveredCharacters());
        assertEquals("incorrect covered characters", List.of('ह'), FOURTEEN.getCoveredCharacters());

        // partial
        assertEquals("incorrect covered characters", List.of('म', 'ङ', 'ण', 'न'), SEVEN.getCoveredCharacters('म'));
        assertEquals("incorrect covered characters", List.of('ङ', 'ण', 'न'), SEVEN.getCoveredCharacters('ङ'));
        assertEquals("incorrect covered characters", List.of(), TWELVE.getCoveredCharacters('य'));

    }
    @Test
    public void allSutraaniTest() {
        List<ShivaSutra> following1 = ONE.following();
        int expCount = 14;
        assertEquals("Does not have all the सूत्राणि", expCount, following1.size());
        assertEquals("First सूत्र is incorrect", ONE, following1.get(0));
        assertEquals("Second सूत्र is incorrect", TWO, following1.get(1));
        assertEquals("Tenth सूत्र is incorrect", TEN, following1.get(9));
    }
    @Test
    public void someSutraaniTest() {
        List<ShivaSutra> following5 = FIVE.following(SEVEN);
        int expCount = 3;
        assertEquals("Does not have all the सूत्राणि", expCount, following5.size());
        assertEquals("First सूत्र is incorrect", FIVE, following5.get(0));
        assertEquals("Second सूत्र is incorrect", SIX, following5.get(1));
        assertEquals("Tenth सूत्र is incorrect", SEVEN, following5.get(2));
    }

    @Test
    public void testCoveredCharacters() {
        ShivaSutra f = ONE;
        List<Character> cc = f.getCoveredCharacters();
        assertEquals("incorrect covered characters", List.of('अ', 'इ', 'उ'), cc);
    }
}