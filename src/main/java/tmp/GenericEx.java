package tmp;

/**
 * Created by kmhaswade on 2/29/16.
 */
public class GenericEx {
    static class Person implements Comparable<Person> {
        @Override
        public int compareTo(Person o) {
            return 0;
        }
    }

    static class Student extends Person {
    }
}
