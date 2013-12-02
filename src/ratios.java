/*
ID: eriksng1
LANG: JAVA
TASK: ratios
*/


import java.io.*;
import static java.lang.Integer.*;
import static java.lang.Math.*;
import java.util.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

public class ratios {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("ratios.in"));
	int A = sc.nextInt(), B = sc.nextInt(), C = sc.nextInt();
	int[][] feed = new int[3][3];
	for(int i = 0; i < 3; i++)
	    for(int j = 0; j < 3; j++)
		feed[i][j] = sc.nextInt();
        

        PrintWriter p = new PrintWriter(new File("ratios.out"));

        p.close();
        System.exit(0);
    }

}
