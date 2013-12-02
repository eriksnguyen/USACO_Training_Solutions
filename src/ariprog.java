/*
ID: eriksng1
LANG: JAVA
TASK: ariprog
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ariprog {

    static int N, M;
    static boolean[] contains;
    static ArrayList<Integer> list;
    static TreeSet<Pair> out = new TreeSet<Pair>();
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("ariprog.in"));
        N = sc.nextInt();
        M = sc.nextInt();
        sc.close();

        generate();
        
        solve();
        PrintWriter p = new PrintWriter(new File("ariprog.out"));
        for(Pair pair : out)
            p.println(pair);
        if(out.size() == 0)
            p.println("NONE");
        p.close();
        System.exit(0);
    }
    
    static void solve(){
        for(int i = 0; i < list.size() - 1; i++){
            int start = list.get(i);
            for(int j = i + 1; j < list.size() - 1; j++){
                int next = list.get(j);
                int diff = next - start;
                
                if(start + (N - 1) * diff > max())
                    break;
                
                boolean exist = true;
                for(int k = N - 1; k >= 1; k--){
                    if(!contains[start + k*diff]){
                        exist = false;
                        break;
                    }
                }
                
                if(exist){
                    out.add(new Pair(start, diff));
                }
            }
        }
    }
    
    static void generate(){
        Set<Integer> bisquares = new TreeSet<Integer>();
        
        for(int i = 0; i <= M; i++){
            in: for(int j = i; j <= M; j++){
                int val = i*i + j*j;
                bisquares.add(val);
            }
        }
        
        list = new ArrayList<Integer>(bisquares);
        
        contains = new boolean[max() + 1];
        for(int i: list)
            contains[i] = true;
    }

    static int max(){
        return list.get(list.size() - 1);
    }
    
    static int min(){
        return list.get(0);
    }
    
    static class Pair implements Comparable<Pair>{
        final int start, diff;
        public Pair(int s, int d){
            this.start = s;
            this.diff = d;
        }
        
        public int compareTo(Pair p){
            if(p.diff == this.diff) return this.start - p.start;
            return this.diff - p.diff;
        }
        
        public boolean equals(Object o){
            if(!( o instanceof Pair)) return false;
            Pair temp = (Pair) o;
            return temp.start == start && temp.diff == diff;
        }
        
        public String toString(){
            return String.format("%d %d", start, diff);
        }
    }
}
