/*
ID: eriksng1
LANG: JAVA
TASK: maze1
*/


import java.io.*;
import java.util.*;

public class maze1 {

    static int maxX, maxY;
    static char[][] grid;
    static int[][] steps;
    static int[][] maze;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("maze1.in"));
        maxY = sc.nextInt();
        maxX = sc.nextInt();
        sc.nextLine();
        
        grid = new char[maxX*2 + 1][maxY*2 + 1];
        for(int i = 0; i < grid.length; i++){
            String in = sc.nextLine();
            for(int j = 0; j < grid[0].length; j++){
                grid[i][j] = in.charAt(j);
            }
        }
        
        maze = new int[maxX][maxY];
        steps = new int[maxX][maxY];
        
        LinkedList<Point> list = new LinkedList<Point>();
        
        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                int x = 2*i + 1, y = 2*j + 1;
                if(grid[x - 1][y] == ' ') maze[i][j] += 1;//Northern pass
                if(grid[x][y + 1] == ' ') maze[i][j] += 2;//Eastern Pass
                if(grid[x + 1][y] == ' ') maze[i][j] += 4;//Southern Pass
                if(grid[x][y - 1] == ' ') maze[i][j] += 8;//Western pass
            }
        }
        
        for (int i = 0; i < maxX; i++) {//Finds the entrances
            if ((maze[i][maxY - 1] & 2) == 2) {
                list.add(new Point(i, maxY - 1));
                steps[i][maxY - 1] = 1;
            } else if ((maze[i][0] & 8) == 8) {
                list.add(new Point(i, 0));
                steps[i][0] = 1;
            }
        }

        for (int j = 0; j < maxY; j++) {
            if ((maze[0][j] & 1) == 1) {
                list.add(new Point(0, j));
                steps[0][j] = 1;
            } else if ((maze[maxX - 1][j] & 4) == 4) {
                list.add(new Point(maxX - 1, j));
                steps[maxX - 1][j] = 1;
            }
        }
                
        while(!list.isEmpty()){
            Point p = list.pollFirst();
            
            int x = p.x, y = p.y;
            int step = steps[x][y] + 1;
            if(canMove(x - 1, y, step) && ((maze[x][y] & 1) == 1)){
                list.add(new Point(x - 1, y));
                steps[x - 1][y] = step;
            }
            if(canMove(x, y + 1, step) && ((maze[x][y] & 2) == 2)){
                list.add(new Point(x, y + 1));
                steps[x][y + 1] = step; 
            }
            if(canMove(x + 1, y, step) && ((maze[x][y] & 4) == 4)){
                list.add(new Point(x + 1, y));
                steps[x + 1][y] = step;
            }
            if(canMove(x, y - 1, step) && ((maze[x][y] & 8) == 8)){
                list.add(new Point(x, y - 1));
                steps[x][y - 1] = step;
            }
        }
        
        int max = 0;
        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                max = Math.max(max, steps[i][j]);
            }
        }
       

        PrintWriter p = new PrintWriter(new File("maze1.out"));
        p.println(max);
        p.close();
        System.exit(0);
    }
    
    private static boolean canMove(int i, int j, int step){
        return inRange(i, j) && (steps[i][j] == 0 || step < steps[i][j]);
    }
    
    private static boolean inRange(int x, int y){
        return x > -1 && y > -1 && x < maxX && y < maxY;
    }

    static class Point{
        final int x, y;
        public Point(int i, int j){
            this.x = i;
            this.y = j;
        }
    }
}
