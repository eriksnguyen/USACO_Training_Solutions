/*
 ID: eriksng1
 LANG: JAVA
 TASK: packrec
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class packrec {

    static Rect[] rects = new Rect[4];
    static TreeSet<Rect> solutions = new TreeSet<Rect>();
    static int[][] perm = { 
        {0, 1, 2, 3}, {0, 1, 3, 2}, {0, 2, 1, 3}, {0, 2, 3, 1}, {0, 3, 1, 2}, {0, 3, 2, 1},
        {1, 0, 2, 3}, {1, 0, 3, 2}, {1, 2, 0, 3}, {1, 2, 3, 0}, {1, 3, 0, 2}, {1, 3, 2, 0},
        {2, 1, 0, 3}, {2, 1, 3, 0}, {2, 0, 1, 3}, {2, 0, 3, 1}, {2, 3, 1, 0}, {2, 3, 0, 1},
        {3, 1, 2, 0}, {3, 1, 0, 2}, {3, 2, 1, 0}, {3, 2, 0, 1}, {3, 0, 1, 2}, {3, 0, 2, 1},
    };

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("packrec.in"));
        for (int i = 0; i < 4; i++) {
            rects[i] = new Rect(sc.nextInt(), sc.nextInt());
        }
        sc.close();
        
        solve();
        Rect out = solutions.pollFirst();
        int minArea = out.area();
        PrintWriter p = new PrintWriter(new File("packrec.out"));
        p.println(minArea);
        p.println(out.output());
        for(Rect r: solutions){
            if(r.area() > minArea)
                break;
            p.println(r.output());
        }
        p.close();
        System.exit(0);
    }
    
    static void solve(){
        for(int[] p: perm){
            calculateAreaOfPermutation(getRects(p));
        }
    }
    
    static void calculateAreaOfPermutation(Rect[] perm){
        for(int i = 0; i < 16; i++){
            Rect[] curr = Arrays.copyOf(perm, 4);
            for(int bit = 0; bit < 4; bit++){
                if(((i >> bit) & 1) == 1){
                    curr[bit] = curr[bit].rot90();
                }
            }
            calculateAreaOfLayouts(curr);
        }
    }
    
    static void calculateAreaOfLayouts(Rect[] r){
        Rect rect;
        
        //1
        rect = new Rect(max(r[0].length, r[1].length, r[2].length, r[3].length), r[0].width + r[1].width + r[2].width + r[3].width);
        rect.setMsg("Layout 1");
        solutions.add(rect);
        
        //2
        rect = new Rect(max(r[0].length, r[1].length, r[2].length) + r[3].length, Math.max(r[0].width + r[1].width + r[2].width, r[3].width));
	rect.setMsg("Layout 2");
        solutions.add(rect);
        
        //3
        rect = new Rect(Math.max(Math.max(r[0].length, r[1].length) + r[2].length, r[3].length), Math.max(r[0].width + r[1].width, r[2].width)+ r[3].width);
	rect.setMsg("Layout 3");
        solutions.add(rect);
        
        //4
        rect = new Rect(max(r[0].length, r[1].length + r[2].length, r[3].length), r[0].width + Math.max(r[1].width, r[2].width) + r[3].width);
	rect.setMsg("Layout 4");
        solutions.add(rect);
        
        //5
        rect = new Rect(max(r[0].length + r[1].length, r[2].length, r[3].length), Math.max(r[0].width, r[1].width) + r[2].width + r[3].width);
	rect.setMsg("Layout 5");
        solutions.add(rect);
        
        //6
        int width;
	if (r[1].length >= r[2].length + r[3].length) {
            width = max(r[0].width, r[1].width + r[2].width, r[1].width + r[3].width);
        } else if (r[1].length >= r[3].length) {
            width = max(r[0].width + r[2].width, r[1].width + r[2].width, r[1].width + r[3].width);
        } else if (r[3].length < r[0].length + r[1].length) {
            width = max(r[0].width + r[3].width, r[0].width + r[2].width, r[1].width + r[3].width);
        } else { // r[3].length >= r[0].length + r[1].length
            width = max(r[2].width, r[0].width + r[3].width, r[1].width + r[3].width);
        }
        rect = new Rect(Math.max(r[0].length + r[1].length, r[2].length + r[3].length), width);
        rect.setMsg("Layout 6");
        solutions.add(rect);
    }
    
    static int max(int a, int b, int c, int d){
        return Math.max(a, max(b, c, d));
    }
    
    static int max(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
    
    static Rect[] getRects(int[] permutation){
        Rect[] ret = new Rect[4];
        for(int i = 0; i < 4; i++){
            ret[i] = rects[permutation[i]];
        }
        return ret;
    }

    private static class Rect implements Comparable<Rect> {

        public final int length, width;
        private String msg;

        public Rect(int l, int w) {
            length = l;
            width = w;
        }

        @Override
        public int compareTo(Rect r) {
            if(area() != r.area()) {
                return area() - r.area();
            }
            if (minDim() == r.minDim()) {
                return maxDim() - r.maxDim();
            }
            return minDim() - r.minDim();
        }

        @Override
        public boolean equals(Object o){
            return compareTo((Rect) o) == 0;
        }
        
        public void setMsg(String s){
            msg = s;
        }
        
        public int area() {
            return length * width;
        }

        public Rect rot90() {
            return new Rect(width, length);
        }

        public int minDim() {
            return Math.min(length, width);
        }

        public int maxDim() {
            return Math.max(length, width);
        }
        
        public String output(){
            return String.format("%d %d", minDim(), maxDim());
        }
        
        @Override
        public String toString(){
            return String.format("%d %d %s", length, width, msg);
        }
    }
}
