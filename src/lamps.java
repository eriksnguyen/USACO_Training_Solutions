/*
ID: eriksng1
LANG: JAVA
TASK: lamps
*/


import java.io.*;
import java.util.*;

public class lamps {

    static int N, C, count;
    static int[] on, off, lamps;
    static PrintWriter p;
    static ArrayList<String> list = new ArrayList<String>(16);
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
        p = new PrintWriter(new File("lamps.out"));
        
        N = Integer.parseInt(in.readLine().trim());
        C = Integer.parseInt(in.readLine().trim());
        StringTokenizer st = new StringTokenizer(in.readLine());
        on = new int[st.countTokens() - 1];
        for(int i = 0; i < on.length; i++){
            on[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(in.readLine());
        off = new int[st.countTokens() - 1];
        for(int i = 0; i < off.length; i++){
            off[i] = Integer.parseInt(st.nextToken());
        }
        
        lamps = new int[N + 1];
        resetLamps();
        
        solve();
        
        if(count == 0) p.println("IMPOSSIBLE");
        Collections.sort(list);
        for(String s: list)
            p.println(s);
        p.close();
        System.exit(0);
    }
    
    private static void solve(){
        //Each button can be pressed at most 1 time. Pressing 2 is the same as
        //not moving and can be used to get rid of extra button precces
        for(int i = 0; i < 16; i++){
            String num = String.format("%4s",Integer.toString(i + 16, 2)).substring(1);
            int numBits = countBits(num);
            //If more presses than allowed must be done or some oddball press
            //must be added, ignore number
            if(numBits > C || (C - numBits) % 2 == 1)
                continue;
            
            for(int j = 1; j <= N; j++){
                if(num.charAt(0) == '1') lamps[j]++;
                if(num.charAt(1) == '1' && j % 2 == 0) lamps[j]++;
                if(num.charAt(2) == '1' && j % 2 == 1) lamps[j]++;
                if(num.charAt(3) == '1' && j % 3 == 1) lamps[j]++;
                lamps[j] %= 2;
            }
            
            boolean okay = true;
            for(int j = 0; j < on.length; j++){
                if(lamps[on[j]] == 0){
                    okay = false;
                    break;
                }
            }
            for(int j = 0; j < off.length; j++){
                if(lamps[off[j]] == 1){
                    okay = false;
                    break;
                }
            }
            if(okay){
                print();
            }
            
            resetLamps();
        }
    }
    
    private static void resetLamps(){
        for(int i = 1; i <= N; i++){ // set lamps on
            lamps[i] = 1;
        }
    }
    
    private static int countBits(String num){
        int ret = 0;
        for(int i = 0; i < num.length(); i++)
            if(num.charAt(i) == '1') ret++;
        return ret;
    }
    
    private static void print(){
        count++;
        StringBuilder b = new StringBuilder();
        for(int i = 1; i <= N; i++){
            b.append(lamps[i]);
        }
        list.add(b.toString());
    }

}
