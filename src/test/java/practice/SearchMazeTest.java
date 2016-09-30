package practice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kmhaswade on 9/28/16.
 */
public class SearchMazeTest {

    @Test
    public void testExists1() throws Exception {
        boolean[][] maze = new boolean[][] {
                {true, true, false, false},
                {true, false, true, false},
        };
        assertFalse(SearchMaze.exists(0, 0, 1, 3, maze));
    }
    @Test
    public void testExists2() throws Exception {
        boolean[][] maze = new boolean[][] {
                {true, false, true, false, true},
                {true, true, true, false, false},
                {false, false, true, true, true},
                {true, false, false, false, true},
        };
        assertTrue(SearchMaze.exists(0, 0, 3, 4, maze));
    }
}