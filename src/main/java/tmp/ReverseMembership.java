package tmp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A good <a href="http://stackoverflow.com/questions/37468161/count-number-of-occurrences-in-various-lists-java">
 *     SO Problem</a>.
 *     <p>
 *         I've got in Java a map of this type Map<Group, List<Person>> that is a set of groups
 *         with the whole list of members.
 *         I want to find the Person that is in the largest number of groups
 *     </p>
 * Created by kmhaswade on 5/26/16.
 */
public class ReverseMembership {
    private static final class Group {
        final String name;
        public Group(String name) {
            this.name = name;
        }
    }
    private static final class Person {
        final String name;
        public Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Map<Group, List<Person>> groups = new HashMap<>(4);
        groups.put(new Group("g1"), Arrays.asList("p1", "p2", "p3", "p4").stream().map(Person::new).collect(Collectors.toList()));
        groups.put(new Group("g2"), Arrays.asList("p1", "p3", "p4").stream().map(Person::new).collect
                (Collectors.toList()));
        groups.put(new Group("g3"), Arrays.asList("p1", "p2").stream().map(Person::new).collect
                (Collectors.toList()));
        groups.put(new Group("g2"), Arrays.asList("p2", "p4").stream().map(Person::new).collect
                (Collectors.toList()));
        groups.values().stream().flatMap(list -> list.stream());
    }
}
