package org.kedar.java.reallyimp.ch1;

import java.io.File;
import java.util.Arrays;

/**
 * <p>
 *     Given an array of File objects, sort it so that the directories come before the files,
 *     and within each group, elements are sorted by path name. Use a lambda expression, not a Comparator.
 * </p>
 * <p>
 *     In the first attempt, I do not parameterize the types of files and sorting criterion.
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class ListerP4 {
    public static void main(String[] args) {
        listNoBehaviorParameterization("/etc");
    }
    private static void listNoBehaviorParameterization(String dir) {
        File[] files = new File(dir).listFiles();
        Arrays.sort(files, (f, g) -> {
            if (f.isDirectory()) {
                if (g.isDirectory())
                    return f.getAbsolutePath().compareTo(g.getAbsolutePath());
                return -1; //directories first
            } else { // first is a file
                if (g.isDirectory())
                    return 1;
                return f.getAbsolutePath().compareTo(g.getAbsolutePath());
            }
        });
        for (File f : files) {
            if (f.isDirectory())
                System.out.print("directory: ");
            else
                System.out.print("file: ");
            System.out.println(f.getName());
        }
    }
}
