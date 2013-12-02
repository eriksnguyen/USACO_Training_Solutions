/*
ID: eriksng1
LANG: JAVA
TASK: milk2
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class milk2 {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("milk2.in"));
        int N = sc.nextInt();
        PriorityQueue<Interval> clocks = new PriorityQueue<Interval>(N);
        for(int i = 0; i < N; i++){
            clocks.add(new Interval(sc.nextInt(), sc.nextInt()));
        }
        sc.close();
        
        ArrayList<Interval> condense = new ArrayList<Interval>(N);
        Interval temp = clocks.poll();
        int maxCont = 0;
        while(clocks.size() > 0){
            Interval next = clocks.poll();
            if(temp.end >= next.start){
                temp = Interval.merge(temp, next);
            } else {
                condense.add(temp);
                maxCont = Math.max(maxCont, temp.end - temp.start);
                temp = next;
            }
        }
        maxCont = Math.max(maxCont, temp.end - temp.start);
        condense.add(temp);
        
        int maxSep = 0;
        for(int i = 1; i < condense.size(); i++){
            maxSep = Math.max(maxSep, condense.get(i).start - condense.get(i-1).end);
        }
        
        PrintWriter p = new PrintWriter(new File("milk2.out"));
        p.println(maxCont + " " + maxSep);
        p.close();
        System.exit(0);
    }
    
    private static class Interval implements Comparable<Interval>{
        
        public final int start, end;
        public Interval(int i1, int i2){
            start = i1;
            end = i2;
        }
        
        public static Interval merge(Interval i1, Interval i2){
            return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
        }
        
        public int compareTo(Interval i){
            return start - i.start;
        }
    }

}
