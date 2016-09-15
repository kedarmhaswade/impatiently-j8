package practice;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static practice.IpAddress.validIp4s;

/**
 * Created by kmhaswade on 9/13/16.
 */
public class IpAddressTest {

    @Test
    public void testValidIp4s() throws Exception {
        String s = "19216811";
        List<String> ip4s = validIp4s(s);
        assertTrue(ip4s.size() == 9);
        assertTrue(ip4s.contains("1.92.168.11"));
        assertTrue(ip4s.contains("19.2.168.11"));
        assertTrue(ip4s.contains("19.21.68.11"));
        assertTrue(ip4s.contains("19.216.8.11"));
        assertTrue(ip4s.contains("19.216.81.1"));
        assertTrue(ip4s.contains("192.1.68.11"));
        assertTrue(ip4s.contains("192.16.8.11"));
        assertTrue(ip4s.contains("192.16.81.1"));
        assertTrue(ip4s.contains("192.168.1.1"));

        s = "0000";
        ip4s = validIp4s(s);
        assertTrue(ip4s.size() == 1);
        assertTrue(ip4s.contains("0.0.0.0"));

        s = "00001";
        ip4s = validIp4s(s);
        assertTrue(ip4s.size() == 0);
        assertFalse(ip4s.contains("0.0.0.0"));

        s = "10000";
        ip4s = validIp4s(s);
        assertTrue(ip4s.size() == 1);
        assertTrue(ip4s.contains("10.0.0.0"));

        s = "00000";
        ip4s = validIp4s(s);
        assertTrue(ip4s.isEmpty());

        s = "5412353656875435345354353";
        System.out.println(validIp4s(s));
    }
}