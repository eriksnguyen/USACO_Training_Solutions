/*
ID: eriksng1
LANG: JAVA
TASK: preface
*/


import java.io.*;
import java.util.*;

public class preface {

    static String N;
    static String[][] vals = {{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                              {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                              {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                              {"", "M", "MM", "MMM"}};
    static int[] count;
    static char[] lett;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("preface.in"));
        N = in.readLine().trim();
        count = new int[7];
        lett = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        int lim = Integer.parseInt(N) + 1;
        for(int i = 1; i < lim; i++){
            String s = piece(i, 0).toString();
            for(int j = 0; j < s.length(); j++){
                switch(s.charAt(j)){
                    case 'I': count[0]++; break;
                    case 'V': count[1]++; break;
                    case 'X': count[2]++; break;
                    case 'L': count[3]++; break;
                    case 'C': count[4]++; break;
                    case 'D': count[5]++; break;
                    case 'M': count[6]++; break;
                }
            }
        }
        
        PrintWriter p = new PrintWriter(new File("preface.out"));
        for(int i = 0; i < 7; i++){
            if(count[i] == 0) continue;
            p.println(lett[i] + " " + count[i]);
        }
        p.close();
        System.exit(0);
    }
    
    static StringBuilder piece(int val, int index){
        if(index == 4)
            return new StringBuilder();
        return new StringBuilder(vals[index][val%10]).append(piece(val/10, index + 1));
    }
}
