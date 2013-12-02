/*
ID: eriksng1
LANG: JAVA
TASK: namenum
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class namenum {

    static HashSet<String> dictionary;
    static HashMap<Character, char[]> mapping;
    static{
        mapping = new HashMap<Character, char[]>();
        mapping.put('2', new char[]{'A', 'B', 'C'});
        mapping.put('3', new char[]{'D', 'E', 'F'});
        mapping.put('4', new char[]{'G', 'H', 'I'});
        mapping.put('5', new char[]{'J', 'K', 'L'});
        mapping.put('6', new char[]{'M', 'N', 'O'});
        mapping.put('7', new char[]{'P', 'R', 'S'});
        mapping.put('8', new char[]{'T', 'U', 'V'});
        mapping.put('9', new char[]{'W', 'X', 'Y'});
    }
    static PrintWriter p;
    static char[] input;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("namenum.in"));
        input = sc.next().toCharArray();
        sc.close();
        
        dictionary = new HashSet<String>();
        sc = new Scanner(new File("dict.txt"));
        while(sc.hasNext()){
            dictionary.add(sc.next());
        }
        sc.close();

        p = new PrintWriter(new File("namenum.out"));
        makeWords("",0);
        if(!found){
            p.println("NONE");
        }
        p.close();
        System.exit(0);
    }
    
    static boolean found = false;
    private static void makeWords(String s, int index){
        if(index == input.length){
            if(dictionary.contains(s)){
                p.println(s);
                found = true;
            }
            return;
        }
        for(char c : mapping.get(input[index])){
            makeWords(s + c, index + 1);
        }
    }

}
