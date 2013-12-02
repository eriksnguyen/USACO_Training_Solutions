/*
ID: eriksng1
LANG: JAVA
TASK: crypt1
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class crypt1 {

    static int N, numSolutions;
    static int[] digits;
    static HashSet<Integer> set;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("crypt1.in"));
        N = sc.nextInt();
        digits = new int[N];
        set = new HashSet<Integer>(N);
        for(int i = 0; i < N; i++)
            set.add(digits[i] = sc.nextInt());
        sc.close();

        markDigits("", "", 0, 0);
        
        PrintWriter p = new PrintWriter(new File("crypt1.out"));
        p.println(numSolutions);
        p.close();
        System.exit(0);
    }

    private static void markDigits(String s1, String s2, int num1, int num2){
        if(num1 < 3){
            for(int i: digits)
                markDigits(s1 + i, s2, num1 + 1, num2);
        } else if(num2 < 2){
            for(int i: digits)
                markDigits(s1, s2 + i, num1, num2 + 1);
        } else {
            multiply(s1, s2);
        }
    }
    
    private static void multiply(String s1, String s2){
        int i1 = Integer.parseInt(s1);
        int i2 = Integer.parseInt(s2);
        
        int partial1 = i1 * (i2%10);
        int partial2 = i1 * (i2/10);
        
        int product = partial1 + partial2 * 10;
        
        String p1S = partial1 + "";
        String p2S = partial2 + "";
        String p3S = product  + "";
        if(p1S.length() == 3 && p2S.length() == 3 && p3S.length() == 4 && inSet(p1S, p2S, p3S))
            numSolutions++;
    }
    
    private static boolean inSet(String s1, String s2, String s3){
        for(int i = 0; i < s1.length(); i++){
            if(!set.contains(s1.charAt(i) - '0'))
                return false;
        }
        for(int i = 0; i < s2.length(); i++){
            if(!set.contains(s2.charAt(i) - '0'))
                return false;
        }
        for(int i = 0; i < s3.length(); i++){
            if(!set.contains(s3.charAt(i) - '0'))
                return false;
        }
        return true;
    }
}
