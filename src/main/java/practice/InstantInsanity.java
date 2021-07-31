package practice;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static practice.InstantInsanity.Direction.*;
import static practice.InstantInsanity.Direction.COUNTERCLOCKWISE;
import static practice.InstantInsanity.Face.*;
import static practice.InstantInsanity.RotAxis.*;

/**
 * <p>
 * Eric Roberts's Thinking Recursively asks this problem about arranging in a tower the given four cubes with
 * faces colored with specific colors, such that all the four colors are visible on each side of the tower. This puzzle
 * was marketed and sold under the name "Instant Insanity" before Rubik's Cube took over the world.
 * </p>
 * <p>
 * This is a good problem to understand how the recursive backtracking works. Of course, there are more
 * efficient algorithms to achieve the objective.
 * </p>
 * <p>
 * A face color is specified by the first letter of the name of the color (e.g. 'G' stands for green).
 * </p>
 * <p>
 * Each cube has a specific configuration in terms of 6 face colors, for example, the configuration "GBGRWB"
 * means that when the cube is placed on a table, its north-face is green, west-face blue, south-face green,
 * east-face red, top-face white, and bottom-face blue. A tower's cubes are numbered from bottom to top.
 * </p>
 */
public class InstantInsanity {

    public static void main(String[] args) {
        Tower t = new Tower(List.of(new Cube("GBGRWB"), new Cube("RWGBWG"),
            new Cube("WRGBRW"), new Cube("BRWRGR")));
        int steps = bruteForceIterative(t);
        if (steps == -1) {
            System.out.println("The tower is insane!");
        } else {
            System.out.println("It took " + steps + " steps");
        }
    }

    /**
     * Solves the tower iteratively. This is an inefficient, exhaustive search. The worst-case complexity is 24^4*4! because
     * there are 24 rotations of each cube and there are 4! permutations of 4 cubes.
     *
     * @param t The tower whose 0th cube is at the bottom and 4th cube at the top.
     * @return integer number of steps necessary to solve if it is solvable, -1 otherwise
     */
    static int bruteForceIterative(Tower t) {
        int steps = 0;
        for (Cube bottom : t.cubes.get(0).getRotationGroup()) {
            for (Cube first : t.cubes.get(1).getRotationGroup()) {
                for (Cube second : t.cubes.get(2).getRotationGroup()) {
                    for (Cube top : t.cubes.get(3).getRotationGroup()) {
                        Tower nt = new Tower(List.of(bottom, first, second, top));
                        if (nt.isSane()) {
                            System.out.println(nt);
                            return steps;
                        }
                        steps += 1;
                    }
                }
            }
        }
        return -1;
    }

    enum Color {
        BLUE, GREEN, RED, WHITE;

        static Color fromMnemonic(char c) {
            switch (c) {
                case 'B':
                    return BLUE;
                case 'G':
                    return GREEN;
                case 'R':
                    return RED;
                case 'W':
                    return WHITE;
                default:
                    throw new IllegalStateException("invalid mnemonic: " + c);
            }
        }
    }

     enum Face {
        // initialized this way to maintain an iteration order
        NORTH,
        WEST,
        SOUTH,
        EAST,
        TOP,
        BOTTOM
    }

     enum RotAxis {
        /**
         * +ve X-axis: to the {@linkplain InstantInsanity.Face#EAST}
         */
        X,

        /**
         * +ve Y-axis: to the {@linkplain InstantInsanity.Face#SOUTH}
         */
        Y,
        /**
         * +ve Z-axis: to the {@linkplain InstantInsanity.Face#TOP}
         */
        Z
    }

    enum Direction {
        COUNTERCLOCKWISE, CLOCKWISE;

        Direction opposite() {
            if (this == CLOCKWISE) {
                return COUNTERCLOCKWISE;
            }
            return CLOCKWISE;
        }
    }

    static class Cube {
        private final Map<Face, Color> faces;
        private final String config;

        Cube(String config) {
            if (config.length() != 6) {
                throw new IllegalArgumentException("invalid config: " + config);
            }
            faces = new HashMap<>(6);
            int i = 0;
            for (Face face : Face.values()) {
                faces.put(face, Color.fromMnemonic(config.charAt(i)));
                i += 1;
            }
            this.config = config;
        }

        Cube(Map<Face, Color> faces) {
            this.faces = faces;
            this.config = getConfigurationFromMap(faces);
        }

        @Override
        public boolean equals(Object that) {
            if (this == that)
                return true;
            if (!(that instanceof Cube))
                return false;
            Cube other = (Cube) that;
            return this.config.equals(other.config);
        }

        @Override
        public int hashCode() {
            return this.config.hashCode();
        }

        @Override
        public String toString() {
            return "NWSETB config: " + config;
        }

        public String getConfig() {
            return config;
        }

        public Map<Face, Color> getFaces() {
            return faces;
        }

        private static String getConfigurationFromMap(Map<Face, Color> mapOfFaces) {
            StringBuilder sb = new StringBuilder(6);
            for (Face face : Face.values()) {
                sb.append(mapOfFaces.get(face).name().charAt(0));
            }
            return sb.toString();
        }

        /**
         * Rotates this cube around the given axis in the direction through given number of turns.
         * The number of turns is modulo 4.
         *
         * @param axis  the {@link InstantInsanity.RotAxis} of rotation; may not be null
         * @param dir   the {@link InstantInsanity.Direction} of rotation around the axis; may not be null.
         * @param turns the number of turns -- an integer modulo 4 specifying the number of turns; if zero,
         * @return a *new* {@linkplain Cube} that represents the specified rotation of this cube; returns
         * itself (not a copy), if the number of turns is zero.
         */
        Cube rotate(@Nonnull RotAxis axis, @Nonnull Direction dir, int turns) {
            turns %= 4; // remember the peculiarities with negative values
            if (turns < 0) {
                dir = dir.opposite();
            }
            if (turns == 0) {
                return this;
            }
            // Use the right-handed rule -- when the right thumb points in an axis of rotation,
            // rotation is always in the direction of curled fingers
            if (dir == CLOCKWISE) {
                turns = 4 - turns;
                dir = COUNTERCLOCKWISE;
            }
            // from now on, only rotate in the counter clockwise direction
            assert dir == COUNTERCLOCKWISE : "programming error -- must always rotate counter clockwise";
            assert turns >= 1 && turns <= 3 : "programming error -- turns: " + turns + " must be in [1, 3]";
            Map<Face, Color> newFaces = null, one, two;
            switch (turns) {
                case 1:
                    newFaces = _rotate1(axis, new HashMap<>(this.faces), this.faces);
                    break;
                case 2:
                    one = _rotate1(axis, new HashMap<>(this.faces), this.faces);
                    newFaces = _rotate1(axis, new HashMap<>(one), one);
                    break;
                case 3:
                    one = _rotate1(axis, new HashMap<>(this.faces), this.faces);
                    two = _rotate1(axis, new HashMap<>(one), one);
                    newFaces = _rotate1(axis, new HashMap<>(two), two);
                    break;
                default:
                    // ok to skip (see the assertions above)
            }
            return new Cube(newFaces); // TODO: consider caching, many intermediate objects are created
        }

        Set<Cube> getRotationGroup() {
            // There are 24 orientations or rotations of a cube, see https://faculty.etsu.edu/beelerr/insanity-supp.pdf
            // 1. Put each face in a particular position (say TOP) -- this is one rotation
            // 2. Do the three rotations around a particular axis (say Z) -- this gives three rotations
            // 3. Add 1. and 2. for all the 6 faces giving 24 rotations in all.
            // The returned Set contains "this" Cube.
            Set<Cube> g = new HashSet<>(24);
            g.add(this);
            addRotationsWithFixedTop(g, this);
            rotateAndAddRotationsWithFixedTop(g, this, NORTH);
            rotateAndAddRotationsWithFixedTop(g, this, WEST);
            rotateAndAddRotationsWithFixedTop(g, this, SOUTH);
            rotateAndAddRotationsWithFixedTop(g, this, EAST);
            rotateAndAddRotationsWithFixedTop(g, this, BOTTOM);

            assert g.size() <= 24 : "rotation group should contain max 23 elements; this one has: " + g.size();
            return g;
        }

        static void rotateAndAddRotationsWithFixedTop(Set<Cube> g, Cube cube, Face from) {
            // this method brings every face to TOP and then adds it and its rotations to the given set
            // TODO: see if this can be shrunk with the help of a data structure so that the conditional code is removed
            Cube toTop = null; // represents the cube which has the given face (from) at the TOP
            switch (from) {
                case NORTH:
                    toTop = cube.rotate(X, COUNTERCLOCKWISE, 1);
                    break;
                case WEST:
                    toTop = cube.rotate(Y, CLOCKWISE, 1);
                    break;
                case SOUTH:
                    toTop = cube.rotate(X, CLOCKWISE, 1);
                    break;
                case EAST:
                    toTop = cube.rotate(Y, COUNTERCLOCKWISE, 1);
                    break;
                case BOTTOM:
                    toTop = cube.rotate(X, COUNTERCLOCKWISE, 2);
                    break;
                default:
                    break;
            }
            assert toTop != null : "cube may not be null";
            g.add(toTop);
            addRotationsWithFixedTop(g, toTop);
        }

        static void addRotationsWithFixedTop(Set<Cube> g, Cube cube) {
            for (int i = 1; i <= 3; i++) {
                Cube r = cube.rotate(Z, COUNTERCLOCKWISE, i);
                boolean added = g.add(r);
                if (!added) {
                    System.out.println("rotation group already contained: " + r + " (multiple faces w the same color), skipping it ...");
                }
            }
        }

        private Map<Face, Color> _rotate1(RotAxis axis, Map<Face, Color> to, Map<Face, Color> from) {
            switch (axis) {
                case X:
                    to.put(TOP, from.get(NORTH));
                    to.put(Face.SOUTH, from.get(TOP));
                    to.put(Face.BOTTOM, from.get(Face.SOUTH));
                    to.put(NORTH, from.get(Face.BOTTOM));
                    return to;
                case Y:
                    to.put(TOP, from.get(Face.EAST));
                    to.put(Face.WEST, from.get(TOP));
                    to.put(Face.BOTTOM, from.get(Face.WEST));
                    to.put(Face.EAST, from.get(Face.BOTTOM));
                    return to;
                case Z:
                    to.put(NORTH, from.get(Face.EAST));
                    to.put(Face.WEST, from.get(NORTH));
                    to.put(Face.SOUTH, from.get(Face.WEST));
                    to.put(Face.EAST, from.get(Face.SOUTH));
                    return to;
            }
            throw new IllegalStateException("programming error -- illegal axis: " + axis);
        }
    }

    static class Tower {
        private final List<Cube> cubes;

        Tower(List<Cube> cubes) {
            this.cubes = cubes;
        }

        @Override
        public String toString() {
            int i = 0;
            StringBuilder sb = new StringBuilder(24);
            for (Cube c : cubes) {
                sb.append("Cube ").append(i).append(": ").append(c).append("\n");
                i++;
            }
            return sb.toString();
        }

        public boolean isSane() {
            Map<Face, List<Color>> allFaceColors = getAllFaceColors();
            List<Color> hadTop = allFaceColors.remove(TOP);
            assert !hadTop.isEmpty();
            List<Color> hadBottom = allFaceColors.remove(Face.BOTTOM);
            assert !hadBottom.isEmpty();
            for (Color c : Color.values()) {
                for (Entry<Face, List<Color>> e : allFaceColors.entrySet()) {
                    List<Color> colors = e.getValue();
                    boolean contained = colors.remove(c); // it's critical to remove at most one (first)
                    if (!contained) {
                        //System.out.println("Tower's " + e.getKey() + " face does not have color: " + c);
                        return false;
                    }
                }
            }
            for (Entry<Face, List<Color>> e : allFaceColors.entrySet()) {
                List<Color> colors = e.getValue();
                if (!colors.isEmpty()) {
                    System.out.println("Tower's " + e.getKey() + " face seems to have duplicate colors: " + colors);
                    return false;
                }
            }
            return true;
        }

        Map<Face, List<Color>> getAllFaceColors() {
            // return the TOP and BOTTOM face colors as a bonus
            Map<Face, List<Color>> faceColors = new HashMap<>(6);
            for (Face face : Face.values()) {
                faceColors.put(face, new ArrayList<>(4));
            }
            for (Cube cube : cubes) {
                Map<Face, Color> faces = cube.getFaces();
                for (Entry<Face, Color> e : faces.entrySet()) {
                    Face key = e.getKey();
                    Color val = e.getValue();
                    faceColors.get(key).add(val);
                }
            }
            return faceColors;
        }
    }

    Stream<Tower> permutations() {

        throw new RuntimeException("nyi");
    }
}
