/*
ID: eriksng1
LANG: JAVA
TASK: transform
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class transform {

    static int N;
    static char[][] map1, map2;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("transform.in"));
        N = sc.nextInt();
        map1 = new char[N][N];
        map2 = new char[N][N];
        for(int i = 0; i < N; i++){
            map1[i] = sc.next().toCharArray();
        }
        for(int i = 0; i < N; i++){
            map2[i] = sc.next().toCharArray();
        }
        sc.close();
        
        int out = 0;
        if(equals(rotate1(map1), map2)){
            out = 1;
        } else if(equals(rotate2(map1), map2)){
            out = 2;
        } else if(equals(rotate3(map1), map2)){
            out = 3;
        } else if(equals(reflectH(map1), map2)){
            out = 4;
        } else if(equals(rotate1(reflectH(map1)), map2) || equals(rotate2(reflectH(map1)), map2) ||
                equals(rotate3(reflectH(map1)), map2)){
            out = 5;
        } else if(equals(map1, map2)){
            out = 6;
        } else {
            out = 7;
        }
        
        PrintWriter p = new PrintWriter(new File("transform.out"));
        p.println(out);
        p.close();
        System.exit(0);
    }
    
    private static boolean equals(char[][] ary1, char[][] ary2){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(ary1[i][j] != ary2[i][j])
                    return false;
            }
        }
        return true;
    }
    
    private static char[][] rotate1(char[][] map1){
        char[][] ret = new char[N][N];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret[i][j] = map1[N - j - 1][i];
            }
        }
        
        return ret;
    }
    
    private static char[][] rotate2(char[][] map1){
        char[][] ret = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret[N-i-1][N-j-1] = map1[i][j];
            }
        }
        return ret;
    }
    
    private static char[][] rotate3(char[][] map1){
        char[][] ret = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret[N-j-1][i] = map1[i][j];
            }
        }
        return ret;
    }
    
    private static char[][] reflectH(char[][] map1){
        char[][] ret = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret[i][N-j-1] = map1[i][j];
            }
        }
        return ret;
    }
    
    private static char[][] reflectV(char[][] map1){
        char[][] ret = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret[N-i-1][j] = map1[i][j];
            }
        }
        return ret;
    }
}
