/*
 ID: eriksng1
 LANG: JAVA
 TASK: subset
 */

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class subset {

    static int N, sum;
    static long out;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("subset.in"));
        N = Integer.parseInt(in.readLine().trim());

        sum = N * (N + 1) / 2;

        if (sum % 2 == 0) {
            sum /= 2;
            solve();
        }


        PrintWriter p = new PrintWriter(new File("subset.out"));
        p.println(out);
        p.close();
        System.exit(0);
    }

    private static void solve() {
        long[][] partial = new long[sum + 1][N + 1];
        for (int target = 1; target <= N; target++) {
            for (int pSum = 1; pSum <= sum; pSum++) {
                partial[pSum][target] = partial[pSum][target - 1];
                if (pSum - target >= 1) {
                    partial[pSum][target] += partial[pSum - target][target - 1];
                }
                if (pSum == target) {
                    partial[pSum][target]++;
                }
            }
        }

        out = partial[sum][N] / 2;
    }
}
