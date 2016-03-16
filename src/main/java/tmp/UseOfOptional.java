package tmp;

import org.omg.CORBA.PERSIST_STORE;

import java.util.Optional;

/** What happens if you have a method f that returns an Optional&lt;T> and T has a method g
 * that returns Optional&lt;U>?
 * Created by kmhaswade on 3/10/16.
 */
public class UseOfOptional {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName(new Name("joe"));
//        name(p).
    }
    static <T extends Person> Optional<Name> name(T t) {
        return t.getName();
    }

    static class Person {
        private Name name;
        public void setName(Name name) {
            this.name = name;
        }

        Optional<Name> getName() {
            return Optional.of(name);
        }
    }
    static class Name {
        private String s;
        public Name(String s) {
            this.s = s;
        }
        public void setIt(String s) {
            this.s = s;
        }
        public String getIt() {
            return s;
        }
    }
}

