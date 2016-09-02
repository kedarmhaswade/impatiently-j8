package tmp.generics;

/**
 * Created by kmhaswade on 8/29/16.
 */
public class Student { // extends Person implements Comparable<Student> { --> fails!

    final String branch;
    public Student(int id, String branch) {
        //super(id);
        this.branch = branch;
    }
}
