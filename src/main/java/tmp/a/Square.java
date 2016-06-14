package tmp.a;

/**
 * Created by kmhaswade on 5/27/16.
 */
public class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("I am square!");
    }

    public static void main(String[] args) {
        Shape s = new Square();
        s.draw();
    }
}
