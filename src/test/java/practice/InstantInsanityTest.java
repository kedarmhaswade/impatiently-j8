package practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.jupiter.api.Assertions.*;
import static practice.InstantInsanity.*;
import static practice.InstantInsanity.Color.*;
import static practice.InstantInsanity.Direction.COUNTERCLOCKWISE;
import static practice.InstantInsanity.Face.*;
import static practice.InstantInsanity.RotAxis.*;

public class InstantInsanityTest {

    @Test
    public void testCubeCtors() {
        // test for "GBGRWB"
        String expConfig = "GBGRWB";
        Cube exp = new Cube(expConfig);
        Map<Face, Color> map = ofEntries(entry(NORTH, GREEN), entry(WEST, BLUE), entry(SOUTH, GREEN),
            entry(EAST, RED), entry(TOP, WHITE), entry(BOTTOM, BLUE));
        Cube act = new Cube(map);
        assertEquals(exp, act, "cubes should be the same");
        assertEquals(expConfig, act.getConfig(), "configs are different -- actual: " + act.getConfig() + ", exp: " + expConfig);
    }

    @Test public void testRotate() {
        // rotate "GBGRWB" by one turn ccw
        String origConfig = "GBGRWB";
        Cube orig = new Cube(origConfig);
        Cube act = orig.rotate(X, COUNTERCLOCKWISE, 1);
        String expConfig = "BBWRGG";
        assertEquals(expConfig, act.getConfig());

        // rotate "GBGRWB" by two turns ccw
        act = orig.rotate(X, COUNTERCLOCKWISE, 2);
        expConfig = "GBGRBW";
        assertEquals(expConfig, act.getConfig());

        // rotate "GBGRWB" by two turns ccw
        act = orig.rotate(X, COUNTERCLOCKWISE, 3);
        expConfig = "WBBRGG";
        assertEquals(expConfig, act.getConfig());
    }

    @Test public void basicTowerTest() {
        Cube first = new Cube("GBGRWB");
        Cube second = new Cube("RWGBWG");
        Cube third = new Cube("WRGBRW");
        Cube fourth = new Cube("BRWRGR");

        Tower t = new Tower(List.of(first, second, third, fourth));
        System.out.println(t.isSane());
    }
}