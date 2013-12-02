/*
ID: eriksng1
LANG: JAVA
TASK: stamps
*/


import java.io.*;
import java.util.*;

public class stamps {

    
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("stamps.in"));
        int K = sc.nextInt();
        int N = sc.nextInt(); sc.nextLine();
        
        int[] stamps = new int[N];
        for(int i = 0; i < N; i++) {
            stamps[i] = sc.nextInt();
        }
        sc.close();
        
        PrintWriter p = new PrintWriter(new File("stamps.out"));
        p.println(solve(N, K, stamps));
        p.close();
        System.exit(0);
    }
    
    public static int solve(int N, int K, int[] stamps){
        Arrays.sort(stamps);
        
        int maxVal = K*stamps[N - 1] + 1;
        int[] minNeeded = new int[maxVal];
        
        //Set every number except 0 to needed more stamps than possible
        for(int i = 1; i < minNeeded.length; i++){
            minNeeded[i] = K + 1;
        }
        
        for(int i = 1; i < minNeeded.length; i++){
            for(int s : stamps){
                if(i - s < 0){
                    continue;
                }
                minNeeded[i] = Math.min(minNeeded[i], minNeeded[i - s] + 1);
            }
            if(minNeeded[i] > K) {
                return i - 1;
            }
        }
        
        return maxVal - 1;
    }

}
