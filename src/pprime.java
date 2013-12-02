/*
 ID: eriksng1
 LANG: JAVA
 TASK: pprime
 */

import java.io.*;
import java.util.*;

public class pprime {

    static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pprime.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        PrintWriter p = new PrintWriter(new File("pprime.out"));
        for (int i = ((A >> 1) << 1) + 1; i <= Math.min(100000, B); i += 2) {
            if (isPalindrome(String.valueOf(i)) && isPrime(i)) {
                p.println(i);
            }
        }

        out6:
        for (int d1 = 1; d1 <= 9; d1 += 2) { /* only odd; evens aren't so prime */
            for (int d2 = 0; d2 <= 9; d2++) {
                for (int d3 = 0; d3 <= 9; d3++) {
                    int i = 100001 * d1 + 10010 * d2 + 1100 * d3;
                    if (i > B ) {
                        break out6;
                    }
                    if(i < A)
                        continue;
                    if (isPrime(i)) {
                        p.println(i);
                    }
                }
            }
        }

        out7:
        for (int d1 = 1; d1 <= 9; d1 += 2) { /* only odd; evens aren't so prime */
            for (int d2 = 0; d2 <= 9; d2++) {
                for (int d3 = 0; d3 <= 9; d3++) {
                    for (int d4 = 0; d4 <= 9; d4++) {
                        int i = 1000001 * d1 + 100010 * d2 + 10100 * d3 + 1000 * d4;
                        if (i > B) {
                            break out7;
                        }
                        if ( i < A){
                            continue;
                        }
                        if (isPrime(i)) {
                            p.println(i);
                        }
                    }
                }
            }
        }

        out8:
        for (int d1 = 1; d1 <= 9; d1 += 2) { /* only odd; evens aren't so prime */
            for (int d2 = 0; d2 <= 9; d2++) {
                for (int d3 = 0; d3 <= 9; d3++) {
                    for (int d4 = 0; d4 <= 9; d4++) {
                        int i = 10000001 * d1 + 1000010 * d2 + 100100 * d3 + 11000 * d4;
                        if (i > B) {
                            break out8;
                        }
                        if (isPrime(i)) {
                            p.println(i);
                        }
                    }
                }
            }
        }

        p.close();
        System.exit(0);
    }

    static boolean isPalindrome(String s) {
        int lim = s.length() / 2;
        for (int i = 0, j = s.length() - 1; i <= lim; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    static boolean isPrime(int val) {
        int lim = (int) (Math.sqrt(val) + 1);
        for (int i = 3; i < lim; i += 2) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
