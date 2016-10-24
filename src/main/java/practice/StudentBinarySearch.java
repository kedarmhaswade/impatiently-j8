package practice;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *     This is just an example of using binary search.
 * </p>
 * Created by kedar on 14/10/16.
 */
public class StudentBinarySearch {

    private static class Student {
        final String name;
        double gpa; // mutable, not part of hashCode, or equals

        Student(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }
        static Student valueOf(String line) {
            String[] parts = line.split("\\s*,\\s*");
            return new Student(parts[0], Double.valueOf(parts[1]));
        }
        static Comparator<Student> DEC_GPA_NAME_COMPARATOR = (Student s1, Student s2) -> {
            int gpaComp = Double.compare(s2.gpa, s1.gpa); // higher gpa first
            return gpaComp == 0 ? s1.name.compareToIgnoreCase(s2.name) : gpaComp;
        };
        @Override
        public String toString() {
            return "(" + name + ", " + gpa + ")";
        }
    }
    public static boolean contains(List<Student> students, Student s) {
        Collections.sort(students, Student.DEC_GPA_NAME_COMPARATOR);
        System.out.println(students);
        return Collections.binarySearch(students, s, Student.DEC_GPA_NAME_COMPARATOR) >= 0;
    }

    public static void main(String[] args) {
        List<Student> list = Stream.of("John, 3.44", "Mary, 4.1")
                .map(Student::valueOf)
                .collect(Collectors.toList());
        System.out.println(contains(list, new Student("Mary", 4.1)));
    }
}
