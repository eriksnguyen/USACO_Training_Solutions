/*
ID: eriksng1
LANG: JAVA
TASK: agrinet
*/


import java.io.*;
import java.util.*;

public class agrinet {

    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int[][] connectivity;
    private static boolean[] intree;
    private static int[] source;
    private static int[] dist;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("agrinet.in"));
        N = Integer.parseInt(sc.nextLine().trim());

        connectivity = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                connectivity[i][j] = sc.nextInt();
            }
        }
        
        intree = new boolean[N];
        source = new int[N];
        dist = new int[N];
        
        PrintWriter p = new PrintWriter(new File("agrinet.out"));
        p.println(prim());
        p.close();
        System.exit(0);
    }
    
    private static int prim(){
        for(int i = 0; i < N; i++){
            dist[i] = INF;
        }
        
        int treeSize = 1;
        int minCost = 0;
        
        intree[0] = true;
        for(int j = 0; j < N; j++){
            dist[j] = connectivity[0][j];
            source[j] = 0;
        }
        
        while(treeSize < N){
            int minDist = INF;
            int index = 0;
            for(int i = 0; i < N; i++){
                if(!intree[i] && dist[i] < minDist){
                    minDist = dist[i];
                    index = i;
                }
            }
            
            treeSize++;
            minCost += minDist;
            intree[index] = true;
            
            for(int i = 0; i < N; i++){
                if(!intree[i] && dist[i] > connectivity[index][i]){
                    dist[i] = connectivity[index][i];
                    source[i] = index;
                }
            }
        }
        
        return minCost;
    }

}
