/*
 ID: eriksng1
 LANG: JAVA
 TASK: sprime
 */

import java.io.*;

public class sprime {

    static int N;
    static PrintWriter p;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
        N = Integer.parseInt(in.readLine().trim());
        p = new PrintWriter(new File("sprime.out"));
        setFirstDigit();
        p.close();
        System.exit(0);
    }

    static void setFirstDigit() {
        setDigit(2, 1);
        setDigit(3, 1);
        setDigit(5, 1);
        setDigit(7, 1);
    }

    static void setDigit(int number, int index) {
        if (!isPrime(number)) {
            return;
        }
        if (index == N) {
            p.println(number);
            return;
        }
        number = number * 10;
        for (int i = 1; i < 10; i += 2) {
            setDigit(number + i, index + 1);
        }
    }

    static boolean isPrime(int val) {
        int lim = (int) Math.sqrt(val) + 1;
        for (int i = 3; i < lim; i += 2) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
