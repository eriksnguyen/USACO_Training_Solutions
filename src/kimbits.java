/*
 ID: eriksng1
 LANG: JAVA
 TASK: kimbits
 */

import java.io.*;
import java.util.*;

public class kimbits {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("kimbits.in"));
        int N = sc.nextInt();
        int L = sc.nextInt();
        long I = sc.nextLong();
        sc.close();

        PrintWriter p = new PrintWriter(new File("kimbits.out"));
        p.println(solve(N, L, I));
        p.close();

        System.exit(0);
    }

    private static String solve(int n, int l, long i) {
        if (n == l) {
            return binary(i - 1, n);
        }
        if (i <= 3) {
            return binary(i - 1, n);
        }

        int n0 = n;
        long count = sum_nCr(n0, l);
        while (count >= i) {
            count = sum_nCr(--n0, l);
        }

        long high, lim = 1 << (n - n0 + 1);
        for (high = 1; high < lim; high++) {
            long newCount = sum_nCr(n0, l - countBits(high));
            if (newCount + count >= i){ break; }
            count += newCount;
        }
        
        return binary(high, n - n0) + solve(n0, l - countBits(high), i - count);
    }

    private static long sum_nCr(int n, int l) {
        long v = 0;
        for (int i = 0; i <= l; i++) {
            v += nCr(n, i);
        }
        return v;
    }

    private static long nCr(int n, int r) {
        long v = 1;
        for (int i = 0; i < r; i++) {
            v *= n - i;
            v /= i + 1;
        }
        return v;
    }

    private static int countBits(long i) {
        int numB = 0;
        while (i > 0) {
            numB += i & 1;
            i >>= 1;
        }
        return numB;
    }

    private static String binary(long l, int len) {
        StringBuilder b = new StringBuilder();
        while (l > 0) {
            b.append(l & 1);
            l >>= 1;
        }
        while (b.length() < len) {
            b.append(0);
        }
        return b.reverse().toString();
    }
}
