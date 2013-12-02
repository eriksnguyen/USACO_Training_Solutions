/*
ID: eriksng1
LANG: JAVA
TASK: gift1
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class gift1 {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("gift1.in"));
        PrintWriter p = new PrintWriter(new File("gift1.out"));
        
        int i = sc.nextInt(); sc.nextLine();
        HashMap<String, Integer> accounts = new HashMap<String,Integer>();
        String[] names = new String[i];
        for(int j = 0; j < i; j++){
            accounts.put(names[j] = sc.nextLine().trim(), 0);
        }
        while(sc.hasNextLine()){
            String name = sc.nextLine().trim();
            int avail = sc.nextInt();
            int numRecip = sc.nextInt(); sc.nextLine();
            if(numRecip == 0){
                continue;
            }
            int give = avail/numRecip;
            accounts.put(name, accounts.get(name) - give*numRecip);
            while(numRecip-- > 0){
                name = sc.nextLine().trim();
                accounts.put(name, accounts.get(name) + give);
            }
        }
        for(String s :names){
            p.println(s + " "+ accounts.get(s));
        }
        p.close();
        sc.close();
        System.exit(0);
    }
}
