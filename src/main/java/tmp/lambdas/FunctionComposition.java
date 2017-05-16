package tmp.lambdas;

import java.util.function.Function;

/**
 * Created by kedar on 5/14/17.
 */
public class FunctionComposition {
    static class Address {

        private String country;

        public String getCountry() {
            return country;
        }
    }

    static class Person {

        private Address address;

        public Address getAddress() {
            return address;
        }
    }

    public static void main(String[] args) {
        Address a = new Address();
        a.country = "US";
        Person p = new Person();
        p.address = a;
        // one way:
        Function<Person, Address> personToAddress = Person::getAddress;
        Function<Address, String> addressToCountry = Address::getCountry;
        Function<Person, String> toCountry = personToAddress.andThen(addressToCountry);
        System.out.println(toCountry.apply(p));
        // easier way
        Function<Person, String> p2c = person -> person.getAddress().getCountry();
        System.out.println(p2c.apply(p));
    }
}
