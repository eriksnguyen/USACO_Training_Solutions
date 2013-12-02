/*
 ID: eriksng1
 LANG: JAVA
 TASK: nocows
 */

import java.io.*;
import java.util.*;

public class nocows {

    static int N, K, out;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        solve();

        PrintWriter p = new PrintWriter(new File("nocows.out"));
        p.println(out);
        p.close();
        System.exit(0);
    }

    private static void solve() {
        int[][] partials = new int[N + 1][K + 1];
        partials[1][1] = 1;

        for (int n = 2; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                int pSum = 0;
                
                    for (int nLeft = 1; nLeft < n - 1; nLeft++) {
                        for (int kRight = 1; kRight < k - 1; kRight++) {
                            pSum += (2 * partials[nLeft][k - 1] * partials[n - 1 - nLeft][kRight] % 9901);
                        }
                        pSum += (partials[nLeft][k - 1] * partials[n - 1 - nLeft][k - 1] % 9901);
                    }

                partials[n][k] = pSum % 9901;
            }
        }
        
        for(int i = 0; i <= N; i++){
            System.out.printf("%2d ", i);
            System.out.println(Arrays.toString(partials[i]));
        }

        out = partials[N][K];
    }
}
