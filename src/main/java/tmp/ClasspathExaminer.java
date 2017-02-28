package tmp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.logging.Level;

/** Examines a classpath for something from jars in it.
 * Created by kedar on 1/5/17.
 */
public class ClasspathExaminer {
    static ClassLoader getContextClassLoader() {
        return AccessController.doPrivileged(
            (PrivilegedAction<ClassLoader>) () -> {
                ClassLoader cl = null;
                try {
                    cl = Thread.currentThread().getContextClassLoader();
                } catch (SecurityException ex) {
                    ex.printStackTrace();
                }
                return cl;
            });
    }
    public static void main(String[] args) throws IOException {
        String serviceId = "META-INF/services/javax.ws.rs.ext.RuntimeDelegate";
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/runtimecp.txt"))) {
            String line;
            ClassLoader classLoader = getContextClassLoader();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
//                Arrays.sort(parts);
                for (String path : parts) {
                    if (path.endsWith(".jar") && path.contains("jersey")) {
                        System.out.println("name: " + path);
                        BufferedReader reader;
                        try {
                            InputStream is;
                            if (classLoader == null) {
                                is = ClassLoader.getSystemResourceAsStream(serviceId);
                            } else {
                                is = classLoader.getResourceAsStream(serviceId);
                            }

                            if (is != null) {
                                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                                System.out.println("service provider: " + reader.readLine());
                            }
                        } catch (Exception ie) {
                            ie.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
