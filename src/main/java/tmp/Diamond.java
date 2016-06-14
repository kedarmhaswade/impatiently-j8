package tmp;

import java.util.Scanner;

/**
 * Created by kmhaswade on 5/17/16.
 */
public class Diamond {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter height: ");
        int tmp = input.nextInt();

        printDiamond(tmp);

        int x = (tmp - 1) * 2 + 1;
        int y = x / 2;
        int z = 1;

        for (int i = 0; i < tmp; i++) {

            for (int j = 0; j <= y; j++) {
                System.out.print(" ");
            }
//            System.out.println((y+1) + " spaces");
            for (int k = 0; k < z; k++) {
                System.out.print("*");
            }
            System.out.println();
            y--;
            z += 2;
        }
        for (int i = 0; i < tmp; i++) {
            y++;
            for (int j = 0; j <= y; j++) {
                System.out.print(" ");
            }
//            System.out.println((y+1) + " spaces");

            z -= 2;
            for (int k = 0; k < z; k++) {
                System.out.print("*");
            }

            System.out.println();

        }
    }
    public static void printDiamond(int h) {
        int ns; // number of spaces
        int na; // number of asterisks
        for (int i = -h; i < h; i++) {
            ns = i == 0 ? 1 : (i < 0 ? Math.abs(i) : i + 1);
            na = i == 0 ? (2*h - 1) : (i < 0 ? (2 * h) - (2 * Math.abs(i))  + 1 : (2 * h) - (2 * i) - 1);
            for (int j = 1; j <= ns; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= na; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
