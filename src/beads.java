/*
ID: eriksng1
LANG: JAVA
TASK: beads
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class beads {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("beads.in"));
        char[] beads = new char[sc.nextInt()];
        String temp = sc.next();
        for(int i = 0; i < beads.length; i++) {
            beads[i] = temp.charAt(i);
        }
        sc.close();
        
        int max = 0;
        for(int left = 0, right = 1; left < beads.length; left++, right++){
            if(left == beads.length - 1)
                right = 0;
            if(beads[right] == 'w' && beads[left] == 'w')
                continue;
            int cLeft, cRight;
            if(beads[left] == 'w')
                cLeft = Math.max(countBeads(beads, 'b', left, -1), countBeads(beads, 'r', left, -1));
            else
                cLeft = countBeads(beads, beads[left], left, -1);
            if(beads[right] == 'w')
                cRight = Math.max(countBeads(beads, 'b', right, 1), countBeads(beads, 'r', right, 1));
            else
                cRight = countBeads(beads, beads[right], right, 1);
            
            max = Math.max(max, cRight + cLeft);
        }
        
        if(max > beads.length || max == 0)
            max = beads.length;
        
        PrintWriter p = new PrintWriter(new File("beads.out"));
        p.println(max);
        p.close();
        System.exit(0);
    }
    
    private static int countBeads(char[] ary, char c, int index, int dir){
        int i = index;
        int count = 0;
        while(ary[i] == c || ary[i] == 'w'){
            if(i == index && count != 0)
                break;
            count++;
            i = (i + dir + ary.length) % ary.length;
        }
        
        return count;
    }
}
