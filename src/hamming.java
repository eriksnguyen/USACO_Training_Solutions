/*
ID: eriksng1
LANG: JAVA
TASK: hamming
*/


import java.io.*;
import java.util.*;

public class hamming {

    static int N, B, D;
    static int[] out;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        out = new int[N];
        solve();
        PrintWriter p = new PrintWriter(new File("hamming.out"));
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < N; i++){
            b.append(out[i]).append(" ");
            if(i%10 == 9){
                p.println(b.toString().trim());
                b = new StringBuilder();
            }
        }
        if(b.length() != 0)
            p.println(b.toString().trim());
        p.close();
        System.exit(0);
    }
    
    static void solve(){
        int maxNum = 1 << (B + 1);
        for(int i = 0, pos = 0; i < maxNum; i++){
            boolean valid = true;
            for(int j = 0; j < pos; j++){
                if(distance(out[j], i) < D){
                    valid = false;
                    break;
                }
            }
            if(valid){
                out[pos++] = i;
            }
            if(pos == N)
                break;
        }
    }   
    
    static int distance(int i1, int i2){
        int dist = 0;
        i1 = i1 ^ i2;
        while(i1 > 0){
            dist += i1 & 1;
            i1 >>= 1;
        }
        return dist;
    }

}
