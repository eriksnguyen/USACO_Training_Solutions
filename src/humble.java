/*
ID: eriksng1
LANG: JAVA
TASK: humble
*/


import java.io.*;
import java.util.*;

public class humble {

    private static int K, N;
    private static int[] nums;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("humble.in"));
        K = sc.nextInt();
        N = sc.nextInt();
        sc.nextLine();
        
        nums = new int[K];
        for(int i = 0; i < K; i++){
            nums[i] = sc.nextInt();
        }
        sc.close();
        
        PrintWriter p = new PrintWriter(new File("humble.out"));
        p.println(solve());
        p.close();
        System.exit(0);
    }
    
    private static long solve(){
        
        ArrayList<Long> numbers = new ArrayList<Long>(N + 1);
        numbers.add(1L);
        
        int[] multiple = new int[K];
        while(numbers.size() < N + 1){
            long next = Long.MAX_VALUE;
            for(int j = 0; j < K; j++){
                next = Math.min(next, nums[j] * numbers.get(multiple[j]));
            }
            numbers.add(next);
            for(int j = 0; j < K; j++){
                while(nums[j] * numbers.get(multiple[j]) <= next){
                    multiple[j]++;
                }
            }
        }
        
        
        return numbers.get(N);
    }

}
