package tmp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by kmhaswade on 4/7/16.
 */
public class Grouping {
    enum Color {
        RED, BLUE, GREEN;
    }

    private static final class Item {
        final Color color;

        Item(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    // a counting collector for groups
    static void countingCollector() {
        Map<Color, Long> groups =
                Stream.of(new Item(Color.RED)
                        , new Item(Color.RED)
                        , new Item(Color.BLUE)
                        , new Item(Color.GREEN)
                )
                        .collect(groupingBy(Item::getColor, counting()));
        groups.forEach((color, count) -> System.out.println("number of items with color: " + color + ": " + count));
    }

    private final static class City {
        private final String name;
        private final String state;
        private final long population;

        City(String nsp) {
            //nsp is name, state, population
            String[] a = nsp.split(",");
            this.name = a[0].trim();
            this.state = a[1].trim();
            this.population = Long.valueOf(a[2].trim());
        }

        City(String name, String state, long population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public long getPopulation() {
            return population;
        }


    }

    static void summingCollector() throws IOException {

        Map<String, Long> populationByState =
                Files.lines(Paths.get("cities.csv")).map(City::new)
                        .collect(groupingBy(City::getState, summingLong(City::getPopulation)));
        populationByState.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue(), ((p1, p2) -> Long.compare(p2, p1))))
                .forEach(entry ->
                        System.out.println("State: " + entry.getKey() + ", " + "total population: " + entry.getValue()));
    }

    public static void main(String[] args) throws IOException {
        countingCollector();
        summingCollector();
        mostPopulousCityInState();
    }

    private static void mostPopulousCityInState() throws IOException {
        Files.lines(Paths.get("cities.csv")).map(City::new)
                .collect(groupingBy(City::getState, maxBy(Comparator.comparing(City::getPopulation))))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey()))
                .forEach(e -> {
                    City city = e.getValue().get();
                    System.out.println(e.getKey() + ", " + city.getName() + " [" + city.getPopulation() + "]");
                });
    }

}
