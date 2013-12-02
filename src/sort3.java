/*
ID: eriksng1
LANG: JAVA
TASK: sort3
*/


import java.io.*;
import java.util.*;

public class sort3 {

    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
        N = Integer.parseInt(in.readLine().trim());

        int[] unsorted = new int[N];
        int[] sorted = new int[N];
        for(int i = 0; i < N; i++){
            sorted[i] = unsorted[i] = Integer.parseInt(new StringTokenizer(in.readLine()).nextToken());
        }
        
        Arrays.sort(sorted);
        int[][] map = new int[4][4];
        for(int i = 0; i < N; i++){
            if(sorted[i] != unsorted[i]){
                map[unsorted[i]][sorted[i]]++ ;
            }
        }
        
        int out = 0;
        
        for(int i = 1; i < 4; i++){
            for(int j = i + 1; j < 4; j++){
                int swap = Math.min(map[i][j], map[j][i]);
                out += swap;
                map[i][j] -= swap;
                map[j][i] -= swap;
            }
        }
        
        for(int i = 2; i < 4; i++){
            out += 2 * map[1][i];
        }

        PrintWriter p = new PrintWriter(new File("sort3.out"));
        p.println(out);
        p.close();
        System.exit(0);
    }

}
