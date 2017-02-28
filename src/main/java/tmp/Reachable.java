package tmp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kedar on 2/22/17.
 */
public class Reachable {
    public static void main(String[] args) {
        long t;
        long t1 = System.currentTimeMillis();
        try {
            URL url = new URL("http", "artifactory.uber.internal", 4567, "/");
            url.openConnection().connect();
            System.out.println("good, you are on VPN!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException i) {
            t = System.currentTimeMillis() - t1;
            System.out.println("time spent: " + t + " milliseconds on artifactory connection ...");
            System.out.println("are you on VPN?");
        }
    }
}
