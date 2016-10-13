package practice.abb;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<String>();
        input.add("1,28,300.1,San Francisco");
        input.add("4,5,209.1,San Francisco");
        input.add("20,7,208.1,San Francisco");
        input.add("23,8,207.1,San Francisco");
        input.add("16,10,206.1,Oakland");
        input.add("1,16,205.1,San Francisco");
        input.add("6,29,204.1,San Francisco");
        input.add("7,20,203.1,San Francisco");
        input.add("8,21,202.1,San Francisco");
        input.add("2,18,201.1,San Francisco");
        input.add("2,30,200.1,San Francisco");
        input.add("15,27,109.1,Oakland");
        input.add("10,13,108.1,Oakland");
        input.add("11,26,107.1,Oakland");
        input.add("12,9,106.1,Oakland");
        input.add("13,1,105.1,Oakland");
        input.add("22,17,104.1,Oakland");
        input.add("1,2,103.1,Oakland");
        input.add("28,24,102.1,Oakland");
        input.add("18,14,11.1,San Jose");
        input.add("6,25,10.1,Oakland");
        input.add("19,15,9.1,San Jose");
        input.add("3,19,8.1,San Jose");
        input.add("3,11,7.1,Oakland");
        input.add("27,12,6.1,Oakland");
        input.add("1,3,5.1,Oakland");
        input.add("25,4,4.1,San Jose");
        input.add("5,6,3.1,San Jose");
        input.add("29,22,2.1,San Jose");
        input.add("30,23,1.1,San Jose");

        ArrayList<Listing> listings = new ArrayList<>();
        for (String s : input) {
            listings.add(Listing.valueOf(s));
        }
        paginate(listings, 6);
    }
    static class Listing {
        final int hostId;
        final String listingId;
        final double score;
        final String city;

        Listing(int hostId, String listingId, double score, String city) {
            this.hostId = hostId;
            this.listingId = listingId;
            this.score = score;
            this.city = city;
        }
        public static Listing valueOf(String s) {
            String[] parts = s.split(",");
            return new Listing(Integer.parseInt(parts[0]), parts[1], Double.valueOf(parts[2]), parts[3]);
        }
        @Override
        public String toString() {
            return hostId + ", " + listingId + ", " + score + ", " + city;
        }
    }

    // prints the listings per page according to the rules
    public static void paginate(ArrayList<Listing> list, int entries) {
        int pageNo = 0;
        while(!list.isEmpty()) {
            System.out.println("page: " + pageNo++);
            Iterator<Listing> iter = list.iterator();
            Set<Integer> set = new HashSet<>();
            int printed = 0;
            while (printed < entries) {
                if (!iter.hasNext())
                    break;
                Listing candidate = iter.next();
                if (!set.contains(candidate.hostId)) {
                    set.add(candidate.hostId);
                    System.out.println(candidate);
                    iter.remove();
                    printed += 1;
                }
            }
        }
    }
}