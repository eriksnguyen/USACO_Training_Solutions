/*
ID: eriksng1
LANG: JAVA
TASK: concom
*/


import java.io.*;
import java.util.*;

public class concom {

    static PrintWriter p;
    static int N;
    static int[][] own;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("concom.in"));
        p = new PrintWriter(new File("concom.out"));
        N = sc.nextInt();
        
        own = new int[101][101];
        for(int i = 0; i < N; i++){
            own[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        }
        
        sc.close();
        
        
        
        solve();

        p.close();
        System.exit(0);
    }

    
    static void solve(){
        boolean[][] owns = new boolean[101][101];
        
        ArrayList<Integer> toCheck = new ArrayList<Integer>(100);
        HashSet<Integer> checked = new HashSet<Integer>(100);
        
        for(int i = 1; i < 101; i++){
            int[] percent = new int[101];
            toCheck.clear();
            checked.clear();
            checked.add(i);
            for(int j = 1; j < 101; j++){
                if((percent[j] = own[i][j]) > 50 ){
                    toCheck.add(j);
                    owns[i][j] = true;
                }
            }
                        
            while(toCheck.size() > 0){
                int index = toCheck.remove(toCheck.size() - 1);
                checked.add(index);
                for(int j = 1; j <101; j++){
                    if((percent[j] += own[index][j]) > 50){
                        if(!checked.contains(j) && !owns[i][j]){
                            toCheck.add(j);
                        }
                        owns[i][j] = true;
                    }
                }
            }
        }
        
        for(int i = 1; i <101; i++){
            for(int j = 1; j <101; j++){
                if(owns[i][j] && i != j){
                    p.println(String.format("%d %d", i, j));
                    //System.out.println(String.format("%d %d", i, j));
                }
            }
        }
    }
}
