/*
 ID:eriksng1
 LANG: JAVA
 TASK: ride
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class ride {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("ride.in"));
        PrintWriter p = new PrintWriter(new File("ride.out"));

        char[] s1 = sc.nextLine().trim().toCharArray();
        char[] s2 = sc.nextLine().trim().toCharArray();

        int sum1 = 1;
        for (char c : s1) {
            sum1 *= c - 64;
        }
        int sum2 = 1;
        for (char c : s2) {
            sum2 *= c - 64;
        }
        p.println((sum1 % 47) == (sum2 % 47) ? "GO" : "STAY");

        p.close();
        sc.close();
        System.exit(0);
    }
}
