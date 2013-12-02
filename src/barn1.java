/*
ID: eriksng1
LANG: JAVA
TASK: barn1
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class barn1 {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("barn1.in"));
        int maxBoards = sc.nextInt(), numStalls = sc.nextInt(), numCows = sc.nextInt();
        int[] locs = new int[numCows];
        for(int i = 0; i < numCows; i++){
            locs[i] = sc.nextInt();
        }
        sc.close();
        
        Arrays.sort(locs);
        
        int maxRange = locs[numCows - 1] - locs[0] + 1;
        
        PriorityQueue<Integer> gaps = new PriorityQueue<Integer>(10 , new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        for(int i = 1; i < numCows; i++){
            int add = locs[i] - locs[i - 1] - 1;
            if(add != 0){
                gaps.add(add);
            }
        }
                
        int b = 1;
        while(gaps.size() > 0 && b <maxBoards){
            maxRange -= gaps.poll();
            b++;
        }

        PrintWriter p = new PrintWriter(new File("barn1.out"));
        p.println(maxRange);
        p.close();
        System.exit(0);
    }

}
