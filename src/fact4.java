/*
ID: eriksng1
LANG: JAVA
TASK: fact4
*/


import java.io.*;
import java.util.*;

public class fact4 {

    static int N;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("fact4.in"));
        N = Integer.parseInt(sc.nextLine().trim());

        int skip2 = findNum5(N);
        
        int lastDigit = 1;
        for(int i = 2, val = i; i <= N; ++i, val = i){
            while(val % 5 == 0) {
                val /= 5;
            }
            
            int min = Math.min(findNumMult(i, 2), skip2);
            val >>= min;
            skip2 -= min;
            
            lastDigit = (val * lastDigit) % 10;
        }
        
        PrintWriter p = new PrintWriter(new File("fact4.out"));
        p.println(lastDigit);
        p.close();
        System.exit(0);
    }
    
    private static int findNum5(int n){
        if(n == 0)
            return 0;
        return n/5 + findNum5(n/5);
    }

    private static int findNumMult(int n, int b){
        if(n % b != 0) {
            return 0;
        }
        return 1 + findNumMult(n/b, b);
    }
}
