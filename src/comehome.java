/*
ID: eriksng1
LANG: JAVA
TASK: comehome
*/


import java.io.*;
import java.util.*;

public class comehome {

    private static int N;
    private static int[][] map;
    private static boolean[][] adj;
    private static int INF = 1000000000;
    private static HashMap<Character, Point> charToPoint;
    public static void main(String[] args) throws IOException{
        charToPoint = new HashMap<Character, Point>(52);
        for(int i = 0; i < 52; i++){
            char c = getChar(i);
            Point p = new Point(c);
            charToPoint.put(c, p);
        }
        
        Scanner sc = new Scanner(new File("comehome.in"));
        N = Integer.parseInt(sc.nextLine().trim());

        map = new int[52][52];
        for(int i = 0; i < 52; i++){
            for(int j = 0; j < 52; j++){
                map[i][j] = INF;
            }
        }
        
        adj = new boolean[52][52];
        for(int n = 0; n < N; n++){
            String[] in = sc.nextLine().trim().split("\\s+");
            int i = getIndex(in[0].charAt(0));
            int j = getIndex(in[1].charAt(0));
            int w = Integer.parseInt(in[2]);
            
            adj[i][j] = adj[j][i] = true;
            map[i][j] = map[j][i] = Math.min(map[i][j], w);
        }
        sc.close();
        
        dijkstra();
        //floydwarshall();
        
        Point pt = findMin();
        System.out.println(pt.val + " " + pt.weight);
        PrintWriter p = new PrintWriter(new File("comehome.out"));
        p.println(pt.val + " " + pt.weight);
        p.close();
        System.exit(0);
    }
    
    private static void floydwarshall(){
        for(int k = 0; k < 52; k++){
            for(int i = 0; i < 52; i++){
                for(int j = i + 1; j < 52; j++){
                    map[i][j] = map[j][i] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }
    
    private static void dijkstra(){
        
        Point start = charToPoint.get('Z');
        
        start.weight = 0;
        PriorityQueue<Point> list = new PriorityQueue<Point>(52);
        list.add(start);
        
        while(!list.isEmpty()){
            Point p = list.poll();
            
            int index = getIndex(p.val);
            for(int i = 0; i < 52; i++){
                if(adj[index][i]){
                    int nVal = p.weight + map[index][i];
                    Point next = charToPoint.get(getChar(i));
                    if(nVal < next.weight){
                        next.weight = nVal;
                        list.add(next);
                    }
                }
            }
        }
    }
    
    private static Point findMin(){
        Point ret = new Point((char)0);
        for(int j = 26; j < 51; j++){
            Point p = charToPoint.get(getChar(j));
            if(p.weight < ret.weight){
                ret = p;
            }
        }
        return ret;
    }
    
    private static char getChar(int i){
        if(i < 26) {
            return (char)('a' + i);
        }
        return (char)('A' + i - 26);
    }
    
    private static int getIndex(char c){
        if(c < 'a') {
            return c - 'A' + 26;
        }
        return c - 'a' ;
    }
    
    private static class Point implements Comparable<Point>{
        public final char val;
        public int weight;
        public Point(char c){
            this.val = c;
            weight = INF;
        }
        
        public int compareTo(Point p){
            return weight - p.weight;
        }
    }
}
