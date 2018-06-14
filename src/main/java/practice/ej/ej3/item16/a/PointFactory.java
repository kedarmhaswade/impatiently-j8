package practice.ej.ej3.item16.a;

public class PointFactory {
    public static Point createOrigin() {
        Point o = new Point();
        o.x = 0.0;
        o.y = 0.0;
        return o;
    }
}
