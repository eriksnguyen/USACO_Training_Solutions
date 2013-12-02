/*
ID: eriksng1
LANG: JAVA
TASK: frac1
*/


import java.io.*;
import java.util.*;

public class frac1 {

    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
        N = Integer.parseInt(in.readLine().trim());

        TreeSet<Fraction> queue = new TreeSet<Fraction>();
        queue.add(new Fraction(0, 1));
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= i; j++){
                queue.add(new Fraction(j, i));
            }
        }

        PrintWriter p = new PrintWriter(new File("frac1.out"));
        for(Fraction f: queue)
            p.println(f);
        p.close();
        System.exit(0);
    }

    private static class Fraction implements Comparable<Fraction>{
        int numerator, denominator;
        public Fraction(int n, int d){
            numerator = n;
            denominator = d;
        }
        
        public boolean equals(Object o){
            Fraction f = (Fraction) o;
            return f.numerator * denominator == numerator * f.denominator;
        }
        
        public int compareTo(Fraction f){
            return numerator * f.denominator - f.numerator * denominator;
        }
        
        public String toString(){
            return numerator + "/" + denominator;
        }
    }
}
