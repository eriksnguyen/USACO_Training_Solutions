/*
ID: eriksng1
LANG: JAVA
TASK: calfflac
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class calfflac {

    static String in, inCopy;
    static Integer[] charLoc;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("calfflac.in"));
        StringBuilder temp = new StringBuilder();
        
        //readin data
        while(sc.hasNextLine()){
            temp.append(sc.nextLine());
            if(sc.hasNextLine())
                temp.append("\n");
        }
        sc.close();
        
        in = temp.toString();
        inCopy = in.toLowerCase();
        charLoc = findLoc();
        
        int maxSize = 0;
        int left = 0, right = 0;
        for(int i = 0; i < charLoc.length; i++){
            int[] info = palin(i, i);//center at i (odd length)
            if(info[2] > maxSize){
                maxSize = info[2];
                left = info[0];
                right = info[1];
            }
            info = palin(i, i + 1);//center between i and i + 1 (even length)
            if(info[2] > maxSize){
                maxSize = info[2];
                left = info[0];
                right = info[1];
            }
        }
        
        PrintWriter p = new PrintWriter(new File("calfflac.out"));
        p.println(maxSize);
        p.println(in.substring(charLoc[left], charLoc[right] + 1));
        p.close();
        System.exit(0);
    }
    
    private static int[] palin(int left, int right){
        for(; left > -1 && right < charLoc.length; left--, right++){
            if(inCopy.charAt(charLoc[left]) != inCopy.charAt(charLoc[right]))
                break;
        } 
        return new int[]{left + 1, right - 1, right - left - 1};
    }
    
    private static Integer[] findLoc(){
        ArrayList<Integer> charLoc = new ArrayList<Integer>(in.length()/2);
        for(int i = 0; i < in.length(); i++){
            if(Character.isLetter(in.charAt(i)))
                charLoc.add(i);
        }
        return charLoc.toArray(new Integer[charLoc.size()]);
    }
}
