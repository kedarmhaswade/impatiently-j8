package tmp;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Demonstrate how to use maps to collect data from a stream.
 * Created by kmhaswade on 3/23/16.
 */
public class CollectToMapEx {
    private final static class Person {
        final int id;
        Person(int id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "id: " + id;
        }
        int getId() {
            return id;
        }
    }

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 1)
                .map(Person::new)
                //.collect(Collectors.toMap(Person::getId, Function.identity())) // does not work with dup keys
                .collect(Collectors.toMap(Person::getId,
                        Function.identity(),
                        (ev, nv) -> nv))
                .forEach((k, v) -> System.out.println(v.toString()));
        printAllLanguagesForCountries();
    }
    static void printAllLanguagesForCountries() {
        // here, we need a Map<String, Set<String>>
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguageSets = locales.collect(
                Collectors.toMap(
                        l -> l.getDisplayCountry(),
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> { // Union of a and b
                            Set<String> r = new HashSet<>(a);
                            r.addAll(b);
                            return r;
                        }));
        // A multimap is what we really need, rather than doing this union business
        countryLanguageSets.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> System.out.println("country: " + e.getKey() + ", languages: " + e.getValue()));
    }
}
