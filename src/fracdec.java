/*
ID: eriksng1
LANG: JAVA
TASK: fracdec
*/


import java.io.*;
import java.util.*;

public class fracdec {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("fracdec.in"));

        int n = sc.nextInt();
        int d = sc.nextInt();
        
        String out = solve(n, d);
        int maxL = 76;
        PrintWriter p = new PrintWriter(new File("fracdec.out"));
        do{
            int len = Math.min(out.length(), maxL);
            p.println(out.substring(0, len));
            out = out.substring(len);
        } while(out.length() > 0);
        p.close();
        System.exit(0);
    }
    
    private static String solve(int num, int denom){
        int gcd = gcd(num, denom);
        num /= gcd;
        denom /= gcd;
        
        int intPart = num / denom;
        num -= intPart * denom;
        if(num == 0) return String.format("%d.0", intPart);
        
        HashMap<Integer, Integer> remainders = new HashMap<Integer, Integer>();
        StringBuilder ret = new StringBuilder();
        ret.append(intPart).append(".");
        
        StringBuilder fraction = new StringBuilder();
        
        int i = 0;
        while(true){
            remainders.put(num, i++);
            num *= 10;
            int m = num / denom;
            fraction.append(m);
            num = num % denom;
            
            if(remainders.containsKey(num)){
                String frac = fraction.toString();
                int d = remainders.get(num);
                ret.append(frac.substring(0, d));
                if(num != 0)
                    ret.append("(").append(frac.substring(d)).append(")");
                break;
            }
        }
        
        return ret.toString();
    }

    private static int gcd(int x, int y){
        if(y == 0) return x;
        if(x < y) return gcd(y, x);
        return gcd(y, x % y);
    }
}
