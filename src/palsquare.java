/*
ID: eriksng1
LANG: JAVA
TASK: palsquare
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class palsquare {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("palsquare.in"));
        int B = sc.nextInt();
        sc.close();

        PrintWriter p = new PrintWriter(new File("palsquare.out"));
        for(int i = 1; i < 301; i++){
            String square = Integer.toString(i * i , B).toUpperCase();
            if(isPalindrome(square)){
                p.println(Integer.toString(i, B).toUpperCase() + " " + square);
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
