/*
ID: eriksng1
LANG: JAVA
TASK: dualpal
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class dualpal {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("dualpal.in"));
        int N = sc.nextInt(), S = sc.nextInt();
        sc.close();

        PrintWriter p = new PrintWriter(new File("dualpal.out"));
        for(int i = S + 1, count = 0; count < N; i++){
            int numPal = 0;
            for(int j = 2; j < 11 && numPal < 2; j++){
                if(isPalindrome(Integer.toString(i, j))){
                    numPal++;
                }
            }
            if(numPal == 2){
                count++;
                p.println(i);
            }
        }
        p.close();
        System.exit(0);
    }
    
    private static boolean isPalindrome(String s){
        for(int i = 0, j = s.length() - 1; i < j; i++, j--){
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }

}
