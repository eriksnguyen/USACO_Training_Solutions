/*
 ID: eriksng1
 LANG: JAVA
 TASK: clocks
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class clocks {

    static int[] values;
    static String out = "";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("clocks.in"));
        values = new int[9];
        for (int i = 0; i < 9; i++) {
            values[i] = sc.nextInt() / 3 % 4;
        }
        sc.close();

        dfs(new int[10], 1);
        PrintWriter p = new PrintWriter(new File("clocks.out"));
        p.println(out.trim());
        p.close();
        System.exit(0);
    }

    static void dfs(int[] ops, int pos) {
        if (out.length() > 0) {
            return;
        }
        if (pos == 10) {
            test(ops);
            return;
        }

        for (int i = 0; i < 4; i++) {
            ops[pos] = i;
            dfs(ops, pos + 1);
        }
    }

    static void test(int[] ops) {
        int[] state = Arrays.copyOf(values, values.length);
        for (int i = 1; i < 10; i++) {
            switch (i) {
                case 1: edit(state, ops[i], 0, 1, 3, 4);    break;
                case 2: edit(state, ops[i], 0, 1, 2);       break;
                case 3: edit(state, ops[i], 1, 2, 4, 5);    break;
                case 4: edit(state, ops[i], 0, 3, 6);       break;
                case 5: edit(state, ops[i], 1, 3, 4, 5, 7); break;
                case 6: edit(state, ops[i], 2, 5, 8);       break;
                case 7: edit(state, ops[i], 3, 4, 6, 7);    break;
                case 8: edit(state, ops[i], 6, 7, 8);       break;
                case 9: edit(state, ops[i], 4, 5, 7, 8);    break;
            }
        }
        for(int i : state){
            if(i % 4 != 0)
                return;
        }
        for(int i = 1; i < 10; i++){
            for(int num = 0; num < ops[i]; num++){
                out += (char) (i + '0') + " ";
            }
        }
    }

    private static void edit(int[] clocks, int val, int... indeces) {
        for (int i : indeces) {
            clocks[i] += val;
        }
    }

}
