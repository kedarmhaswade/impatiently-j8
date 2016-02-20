package org.kedar.java.reallyimp.ch1;

import java.io.File;
import java.io.FilenameFilter;

/**
 * <p>
 *     Using the {@linkplain java.io.File#list(FilenameFilter)}, write a method that returns all files in
 *     a given directory with a given extension. Use a lambda expression, not a FilenameFilter.
 *     Which variables from the enclosing scope does it capture?
 * </p>
 * <p>
 *     This is similar to {@linkplain ListerP2}.
 *     The extension is passed to the list method that is referenced by the lambda expression. That variable is
 *     thus <i>captured</i> by the lambda expression.
 * </p>
 * Created by kmhaswade on 2/19/16.
 */
public class ListerP3 {
    public static void main(String[] args) {
        list("/etc", ".conf");
    }

    private static void list(String dir, String ext) {
        String[] names = new File(dir).list((folder, name) -> name.endsWith(ext));
        for (String name : names)
            System.out.println(name);
    }
}
