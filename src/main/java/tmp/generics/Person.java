package tmp.generics;

/**
 * Created by kmhaswade on 8/29/16.
 */
public class Person implements Comparable<Person> {

    final int id;
    public Person(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Person that) {
        return Integer.compare(this.id, that.id);
    }
}
