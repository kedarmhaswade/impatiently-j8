package practice.tt;

/**
 * Created by kedar on 11/8/16.
 */
public class Driver {
    public static void main(String[] args) {
        Team a = new Team("Riders", "Leo");
        Team b = new Team("Sliders", "Ted");
        TwoTeamGame game = new TwoTeamGame(a, b, 7);
        game.start(System.out::println);
    }
}
