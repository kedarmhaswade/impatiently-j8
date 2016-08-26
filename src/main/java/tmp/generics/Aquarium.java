package tmp.generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kmhaswade on 8/26/16.
 */
public class Aquarium extends Habitat<Fish> {

    public Aquarium() {
        super();
    }
    public Aquarium(Collection<Fish> fishes) {
        super(fishes);
    }

    /**
     * <b>Note:</b> This method overrides the addInhabitant method in the superclass.
     * @param fish
     */
    @Override
    public void addInhabitant(Fish fish) {
        super.addInhabitant(fish);
        /* no casting is necessary and yet the class is type-safe */
//        if (animal instanceof Fish)
//            super.addInhabitant(animal);
//        throw new IllegalArgumentException(animal + " is not a Fish");
    }

    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
//        aquarium.addInhabitant(new Cat()); // compiler error! => type safe

        Collection<Animal> animals = new ArrayList<>();
        animals.add(new Cat());
        //aquarium = new Aquarium(animals); // compile time error: required: Collection<Fish>, but ArrayList<Animal>
    }
}
