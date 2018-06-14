package tmp.b;

import tmp.a.House;

public class Alien {
    public static void mutate(House.ROOM[] values) {
        values[0] = new House.ROOM("red");
    }
}
