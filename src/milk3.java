/*
ID: eriksng1
LANG: JAVA
TASK: milk3
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class milk3 {

    static int[] buckets;
    static int[] filled;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("milk3.in"));
        buckets = new int[3];
        filled = new int[3];
        for(int i = 0; i < 3; i++) {
            buckets[i] = sc.nextInt();
        }
        sc.close();

        filled[2] = buckets[2];
        
        solve();
        
        StringBuilder print = new StringBuilder();
        for(Integer i : out) {
            print.append(i).append(" ");
        }
        PrintWriter p = new PrintWriter(new File("milk3.out"));
        p.println(print.toString().trim());
        p.close();
        System.exit(0);
    }

    static TreeSet<Integer> seen = new TreeSet<Integer>();
    static TreeSet<Integer> out = new TreeSet<Integer>();
    static void solve(){
        if(!seen.add(encode())) {
            return;
        }
        if(filled[0] == 0) {
            out.add(filled[2]);
        }
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == j || filled[j] == buckets[j] || filled[i] == 0) {
                    continue;
                }
                
                int move = Math.min(filled[i], buckets[j] - filled[j]);
                filled[i] -= move;
                filled[j] += move;
                
                solve();
                
                filled[i] += move;
                filled[j] -= move;
            }
        }
    }
    
    static int encode(){
        int ret = 0;
        for(int i: filled){
            ret = ret * 100 + i;
        }
        return ret;
    }
}
