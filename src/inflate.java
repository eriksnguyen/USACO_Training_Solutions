/*
ID: eriksng1
LANG: JAVA
TASK: inflate
*/


import java.io.*;
import java.util.*;

public class inflate {

    private static int M, N, out;
    private static int[] pts, min;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("inflate.in"));
        M = sc.nextInt();
        N = sc.nextInt();
        
        pts = new int[N];
        min = new int[N];
        
        for(int i = 0; i < N; i++){
            pts[i] = sc.nextInt();
            min[i] = sc.nextInt();
        }
        
        PrintWriter p = new PrintWriter(new File("inflate.out"));
        p.println(solve());
        p.close();
        System.exit(0);
    }
    
    public static int solve(){
        int[] dp = new int[M + 1];
        
        for(int t = 1; t <= M; t++){
            for(int i = 0; i < N; i++ ){
                if(t - min[i] < 0) continue;
                dp[t] = Math.max(dp[t], dp[t - min[i]] + pts[i]);
            }
        }
        
        return dp[M];
    }
}
