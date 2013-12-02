/*
ID: eriksng1
LANG: JAVA
TASK: runround
*/


import java.io.*;

public class runround {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("runround.in"));
        long N = Integer.parseInt(in.readLine().trim()) + 1;

        while(!isRunaround(String.valueOf(N))){
            N++;
        }

        PrintWriter p = new PrintWriter(new File("runround.out"));
        p.println(N);
        p.close();
        System.exit(0);
    }

    static boolean isRunaround(String N){
        int length = N.length();
        boolean[] checkDigits = new boolean[10];
        for(int i = 0; i < N.length(); i++){
            if(checkDigits[N.charAt(i) - '0'])
                return false;
            checkDigits[N.charAt(i) - '0'] = true;
        }
        boolean[] place = new boolean[length];
        
        int start = 0;
        while(!place[start]){
            place[start] = true;
            start = (start + N.charAt(start) - '0') % length;
        }
        if(start != 0)
            return false;
        for(boolean b : place){
            if(!b)
                return false;
        }
        return true;
    }
}
