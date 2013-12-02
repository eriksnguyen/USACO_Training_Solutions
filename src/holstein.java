/*
 ID: eriksng1
 LANG: JAVA
 TASK: holstein
 */

import java.io.*;
import java.util.*;

public class holstein {

    static int V, G, minScoops = Integer.MAX_VALUE;
    static Stack<Integer> out, temp;
    static int[] reqs;
    static Scoop[] scoops;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
        V = Integer.parseInt(in.readLine().trim());

        StringTokenizer st = new StringTokenizer(in.readLine());
        reqs = new int[V];
        for (int i = 0; i < V; i++) {
            reqs[i] = Integer.parseInt(st.nextToken());
        }

        G = Integer.parseInt(in.readLine().trim());

        scoops = new Scoop[G];
        for (int i = 0; i < G; i++) {
            scoops[i] = new Scoop(in.readLine().trim().split("\\s+"));
        }

        out = new Stack<Integer>();
        temp = new Stack<Integer>();
        solve(0, 0);

        PrintWriter p = new PrintWriter(new File("holstein.out"));
        p.print(minScoops);
        for (int i : out) {
            p.print(" " + (i + 1));
        }
        p.println();
        p.close();
        System.exit(0);
    }

    static void solve(int numScoops, int i) {
        if (done() && numScoops < minScoops) {
            minScoops = numScoops;
            out.clear();
            out.addAll(temp);
            return;
        }
        if (numScoops >= minScoops || i == G) {
            return;
        }

        for (int j = 0; j < scoops[i].vals.length; j++) {
            reqs[j] -= scoops[i].vals[j];
        }
        temp.push(i);
        solve(numScoops + 1, i + 1);
        temp.pop();
        for (int j = 0; j < scoops[i].vals.length; j++) {
            reqs[j] += scoops[i].vals[j];
        }
        solve(numScoops, i + 1);
    }

    static boolean done() {
        for (int i = 0; i < V; i++) {
            if (reqs[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private static class Scoop {

        int[] vals;

        public Scoop(String[] in) {
            vals = new int[in.length];
            for (int i = 0; i < in.length; i++) {
                vals[i] = Integer.parseInt(in[i]);
            }
        }
    }
}
