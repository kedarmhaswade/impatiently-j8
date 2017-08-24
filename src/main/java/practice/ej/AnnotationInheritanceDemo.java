package practice.ej;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public class AnnotationInheritanceDemo {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface ClassMetric {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface MethodMetric {
    }

    @ClassMetric
    static class A {
        @MethodMetric
        public int getHits() {
            return 0;
        }
    }
    static class B extends A {

    }

    public static void main(String[] args) {
        printAnnotations(new A());
        printAnnotations(new B());
    }
    private static void printAnnotations(Object o) {
        System.out.println("class annotations for: " + o.getClass().getName());
        Arrays.stream(o.getClass().getAnnotations()).forEach(System.out::println);
        System.out.println("method annotations for: " + o.getClass().getName());
        Arrays.stream(
            o.getClass().getDeclaredMethods())
                    .flatMap(m -> Arrays.stream(m.getDeclaredAnnotations())
                    )
            .forEach(System.out::println);

    }
}
