/*
ID: eriksng1
LANG: JAVA
TASK: milk
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class milk {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("milk.in"));
        int numGal = sc.nextInt(), numFarm = sc.nextInt();
        
        PriorityQueue<Farmer> list = new PriorityQueue<Farmer>();
        for(int i = 0; i < numFarm; i++){
            list.add(new Farmer(sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        int price = 0;
        while(numGal > 0){
            Farmer f = list.poll();
            if(numGal < f.maxGal){
                price += f.price * numGal;
                numGal = 0;
            } else {
                price += f.maxGal * f.price;
                numGal -= f.maxGal;
            }
        }
        
        PrintWriter p = new PrintWriter(new File("milk.out"));
        p.println(price);
        p.close();
        System.exit(0);
    }
    
    public static class Farmer implements Comparable<Farmer>{
        final int price, maxGal;
        public Farmer(int p, int g){
            this.price = p;
            this.maxGal = g;
        }
        
        public int compareTo(Farmer f){
            return price - f.price;
        }
    }
}
