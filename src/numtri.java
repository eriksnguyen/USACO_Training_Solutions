/*
ID: eriksng1
LANG: JAVA
TASK: numtri
*/


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class numtri {

    static int[][] tri;
    static int N;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("numtri.in"));
        N = Integer.parseInt(sc.nextLine().trim());
        tri = new int[N][N];
        for(int i = 0; i < N; i++){
            String[] in = sc.nextLine().trim().split("\\s+");
            for(int j = 0; j <= i; j++){
                tri[i][j] = Integer.parseInt(in[j]);
            }
        }
        sc.close();
        
        for(int i = N - 2; i > -1; i--){
            for(int j = 0; j <= i; j++){
                tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
            }
        }

        PrintWriter p = new PrintWriter(new File("numtri.out"));
        p.println(tri[0][0]);
        p.close();
        System.exit(0);
    }

}
