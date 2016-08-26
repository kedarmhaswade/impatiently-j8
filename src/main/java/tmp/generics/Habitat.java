package tmp.generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kmhaswade on 8/26/16.
 */
public abstract class Habitat<A extends Animal> {
    private final Collection<A> animals = new ArrayList<>();

    public Habitat() {

    }
    public Habitat(Collection<A> animals) {
        animals = new ArrayList<>(animals);
    }
    /**
     * Adds the given animal.
     * @param a
     */
    public void addInhabitant(A a) {
        animals.add(a);
    }
}
