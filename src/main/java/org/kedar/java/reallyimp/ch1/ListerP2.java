package org.kedar.java.reallyimp.ch1;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * <p>
 *     Using the {@linkplain java.io.File#listFiles(FileFilter)} and {@linkplain File#isDirectory()},
 *     write a method that returns all <code>subdirectories</code> of a given directory.
 *     Use a lambda expression instead of a FileFilter object.
 *     Repeat with a method reference.
 * </p>
 * <p>
 *     This seems straightforward enough.
 * </p>
 * Created by kmhaswade on 2/19/16.
 */
public class ListerP2 {
    public static void main(String[] args) {
        String dir = "/";
        listSubdirSexp(dir);
        System.out.println();
        listSubdirMethodRef(dir);
    }

    private static void listSubdirSexp(String dir) {
        Arrays.asList(new File(dir).listFiles(f -> f.isDirectory())).forEach(System.out::println);
    }
    private static void listSubdirMethodRef(String dir) {
//        Arrays.asList(new File(dir).listFiles(File::isDirectory)).forEach(System.out::println);
        for (File f : new File(dir).listFiles(File::isDirectory))
            System.out.println(f.getName());
    }
}
