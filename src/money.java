/*
ID: eriksng1
LANG: JAVA
TASK: money
*/


import java.io.*;
import java.util.*;

public class money {

    static int V, N;
    static long count;
    static int[] values;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("money.in"));
        
        
        V = sc.nextInt();
        N = sc.nextInt();

        values = new int[V];
        
        for(int i = 0; i < V; i++){
            values[i] = sc.nextInt();
        }
                
        solve();

        PrintWriter p = new PrintWriter(new File("money.out"));
        p.println(count);
        p.close();
        System.exit(0);
    }

    private static void solve(){
        
        //V coins, and 0->N = N+1 values to hit
        long[][] partials = new long[V][N + 1];
        
        //Values solvable with first coin
        for(int n = 1; n <= N; n++){
            if(n % values[0] == 0) {
                partials[0][n] = 1;
            }
        }
        
        for(int n = 1; n <= N; n++){
            for(int v = 1; v < V; v++){
                int maxCoin = n / values[v] + 1;
                for(int numCoin = 0; numCoin < maxCoin; numCoin++ ){
                    partials[v][n] += partials[v - 1][n - numCoin*values[v]];
                }
                if(n % values[v] == 0)
                    partials[v][n]++;
            }
        }
        
        count = partials[V - 1][N];
    }
}
