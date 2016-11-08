package practice.tt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kedar on 11/8/16.
 */
public class Team {
    private final Set<String> players;
    private final String name;
    public Team(String name, Set<String> players) {
        this.name = name;
        this.players = players;
    }
    public Team(String name, String first, String... remaining) {
        this.name = name;
        this.players = new HashSet<>(1 + remaining.length);
        this.players.add(first);
        for (String m : remaining)
            this.players.add(m);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Team) {
            Team that = (Team) o;
            return this.name.equals(that.name) && this.players.equals(that.players);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return 11 * name.hashCode() + 13 * players.hashCode();
    }
    @Override
    public String toString() {
        return "team: " + name + " " + players.toString();
    }
}
