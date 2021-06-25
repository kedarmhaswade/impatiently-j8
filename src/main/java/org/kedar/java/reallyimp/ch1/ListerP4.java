package org.kedar.java.reallyimp.ch1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * Given an array of File objects, sort it so that the directories come before the files,
 * and within each group, elements are sorted by path name. Use a lambda expression, not a Comparator.
 * </p>
 * <p>
 * In the first attempt, I do not parameterize the types of files and sorting criterion.
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class ListerP4 {
    public static void main(String[] args) {

        String dir = "/etc";
        listNoBehaviorParameterization(dir);
        Comparator<File> reversePathSorter = (f, g) -> {
            if (f.isDirectory()) {
                if (g.isDirectory())
                    return g.getAbsolutePath().compareTo(f.getAbsolutePath());
                return -1; //directories first
            } else {
                if (g.isDirectory())
                    return 1;
                return g.getAbsolutePath().compareTo(f.getAbsolutePath());
            }
        };
        System.out.println("Reverse Sorting");
        listParametrizedSorting(dir, reversePathSorter);
    }

    private static void listNoBehaviorParameterization(String dir) {
        File d = new File(dir);
        if (! d.exists())
            return;
        File[] files = d.listFiles();
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
        listFiles(files);
    }

    private static void listParametrizedSorting(String dir, Comparator<File> cmp) {
        File d = new File(dir);
        if (!d.exists())
            return;
        File[] files = d.listFiles();
        Arrays.sort(files, cmp);
        listFiles(files);
    }

    private static void listFiles(File[] files) {
        for (File f : files) {
            if (f.isDirectory())
                System.out.print("directory: ");
            else
                System.out.print("file: ");
            System.out.println(f.getName());
        }
    }
}
