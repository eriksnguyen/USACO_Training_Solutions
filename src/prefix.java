/*
 ID: eriksng1
 LANG: JAVA
 TASK: prefix
 */

import java.io.*;
import java.util.*;

public class prefix {

    private static int out;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("prefix.in"));

        LinkedList<String> primitives = new LinkedList<String>();
        while (true) {
            String[] temp = sc.nextLine().trim().split("\\s+");
            if (temp[0].equals(".")) {
                break;
            }
            primitives.addAll(Arrays.asList(temp));
        }

        StringBuilder temp = new StringBuilder();
        while (sc.hasNextLine()) {
            temp.append(sc.nextLine().trim());
        }

        solve(temp.toString(), primitives.toArray(new String[0]));
        
        PrintWriter p = new PrintWriter(new File("prefix.out"));
        p.println(out);
        p.close();
        System.exit(0);
    }

    static void solve(String word, String[] primitives) {
        int len = word.length();
        boolean[] prefStart = new boolean[len + 1];
        
        for(String s : primitives){
            int l = s.length();
            if(l > len) continue;
            if(word.substring(0, l).equals(s))
                prefStart[l] = true;
        }
        
        for(int start = 1; start <= len; start++){
            if(prefStart[start]){
                for(String s : primitives){
                    int l = s.length();
                    if(start + l > len)
                        continue;
                    if(word.substring(start, start + l).equals(s))
                        prefStart[start + l] = true;
                }
            }
        }
        
        for(int start = 1; start <= len; start++){
            if(prefStart[start])
                out = start;
        }
    }

    
}
