package practice.ej;

public class ConstantExpression {
    public static void main(String[] args) {
        demo1();
    }
    static void demo1() {
        String s1 = "The integer " + Long.MAX_VALUE + " is mighty big.";
        String s2 = "The integer " + Long.MAX_VALUE + " is mighty big.";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
    static void demo2() {
        String[] strings = new String[] {"to ", "be ", "or ", "not ", "to ", "be"};

        String hamlet1 = "", hamlet2 = "";
        for (String s : strings) {
            hamlet1 += s;
        }
        for (String s : strings) {
            hamlet2 += s;
        }
        System.out.println(hamlet1 == hamlet2);
        System.out.println(hamlet1.equals(hamlet2));
    }
}
