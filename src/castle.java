/*
 ID: eriksng1
 LANG: JAVA
 TASK: castle
 */

import java.io.*;
import java.util.*;

public class castle {

    private static int M, N, rooms, maxArea;
    private static Node[][] graph;
    private static int[][] subgraph;
    private static int[]  areas;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("castle.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new Node[N][M];
        subgraph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        
        
        floodFill();
        findMaxArea();
        Wall out = findMaxConnection();

        PrintWriter p = new PrintWriter(new File("castle.out"));
        p.println(rooms);
        p.println(maxArea);
        p.println(out.area);
        p.println(out);
        p.close();
        System.exit(0);
    }

    private static Wall findMaxConnection(){
        PriorityQueue<Wall> choices = new PriorityQueue<Wall>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                Node temp = graph[i][j];
                if(temp.south && i < N - 1 && !(subgraph[i][j] == subgraph[i + 1][j])){
                    choices.add(new Wall(graph[i + 1][j], 1, areas[subgraph[i][j]] + areas[subgraph[i + 1][j]]));
                }
                if(temp.east && j < M - 1 && !(subgraph[i][j] == subgraph[i][j + 1])){
                    choices.add(new Wall(graph[i][j], 2, areas[subgraph[i][j]] + areas[subgraph[i][j + 1]]));
                }
            }
        }
        
        return choices.poll();
    }
    
    private static void findMaxArea(){
        areas = new int[rooms + 1];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                areas[subgraph[i][j]]++;
            }
        }
        
        for(int i : areas){
            maxArea = Math.max(i, maxArea);
        }
        
    }
    
    private static void floodFill() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (subgraph[i][j] == 0) {
                    rooms++;
                    fill(i, j, rooms);
                }
            }
        }
    }

    private static void fill(int row, int col, int color) {
        if(subgraph[row][col] == color){
            return;
        }
        subgraph[row][col] = color;
        if (!graph[row][col].east) {
            fill(row, col + 1, color);
        }
        if (!graph[row][col].south) {
            fill(row + 1, col, color);
        }
        if(!graph[row][col].west){
            fill(row, col - 1, color);
        }
        if(!graph[row][col].north){
            fill(row - 1, col, color);
        }
    }

    private static class Wall implements Comparable<Wall>{
        public final Node module;
        public final int area, direction;
        public Wall(Node n1, int dir, int a){
            module = n1;
            area = a;
            direction = dir;
        }
        
        public int compareTo(Wall o){
            int dArea = o.area - area;
            int dDirection = direction - o.direction;
            int dWest = module.col - o.module.col;
            int dSouth = o.module.row - module.row;
            if(dArea != 0){
                return dArea;
            } else if(dWest != 0){
                return dWest;
            } else if(dSouth != 0){
                return dSouth;
            } else {
                return dDirection;
            }
        }
        
        public String toString(){
            return module.toString() + " " + (direction == 1 ? 'N' : 'E');
        }
    }
    
    private static class Node {

        final int row, col;
        boolean north, east, south, west;

        public Node(int r, int c, int i) {
            row = r;
            col = c;
            west = i % 2 == 1;
            north = (i >>= 1) % 2 == 1;
            east = (i >>= 1) % 2 == 1;
            south = (i >>= 1) % 2 == 1;
        }
        
        public String toString(){
            return (row + 1) + " " + (col + 1);
        }
    }
}
