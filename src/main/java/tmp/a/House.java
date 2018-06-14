package tmp.a;

import tmp.b.Alien;

public class House {
    private static final ROOM[] PRIVATE_ROOMS = {new ROOM("green")};
    public static final ROOM[] values() {
        return PRIVATE_ROOMS.clone();
    }
    public static void main(String[] args) {
        System.out.println("before: " + PRIVATE_ROOMS[0].color);
        Alien.mutate(values());
        System.out.println("after: " + PRIVATE_ROOMS[0].color);
        System.out.println("ha ha mutator, you could only mutate the copy");
    }

    public static final class ROOM {
        private final String color;
        public ROOM(String color) {
            this.color = color;
        }
    }
}
