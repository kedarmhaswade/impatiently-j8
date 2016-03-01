package tmp;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by kmhaswade on 2/26/16.
 */
public class AutoEx {
    static class MyClosingWriter extends StringWriter implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("my close is called ...");
        }
    }
    static class MyWriter extends StringWriter implements AutoCloseable {

    }

    public static void main(String[] args) {
        try (MyClosingWriter closing = new MyClosingWriter()) {
            System.out.println("This is a test ...");
        }
    }
}
