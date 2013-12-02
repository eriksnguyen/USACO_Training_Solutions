/*
ID: eriksng1
LANG: JAVA
TASK: ttwo
*/


import java.io.*;
import java.util.*;

public class ttwo {

    private static char[][] map = new char[10][10];
    private static final int MAXTIME = 160000;
    private static int out;
    private static Point c, f;
    
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("ttwo.in"));
        for(int i = 0; i < 10; i++){
            String in = sc.nextLine().trim();
            for(int j = 0; j < 10; j++){
                map[i][j] = in.charAt(j);
                if(in.charAt(j) == 'C'){
                    c = new Point(i, j);
                }
                if(in.charAt(j) == 'F'){
                    f = new Point(i, j);
                }
            }
        }
        
        out = simulate();
                
        PrintWriter p = new PrintWriter(new File("ttwo.out"));
        p.println(out);
        p.close();
        System.exit(0);
    }

    private static int simulate() {
        int time = 0;
        
        while(!(f.equals(c))){
            move(f);
            move(c);
            time++;
            if(time == MAXTIME) return 0;
        }
        
        return time;
    }
    
    private static void move(Point p){
        int newx = p.moveX();
        int newy = p.moveY();
        if(newx == -1 || newx == 10 || newy == -1 || newy == 10){
            p.turn();
        } else if(map[newx][newy] == '*'){
            p.turn();
        } else {
            p.move();
        }
    }

    private static class Point {
        private static int[] dx = {-1, 0, 1, 0};
        private static int[] dy = {0, 1, 0, -1};
        
        private int x, y, dir;
        public Point(int i, int j){
            this.x = i;
            this.y = j;
        }
        
        public void move(){
            this.x = moveX();
            this.y = moveY();
        }
        
        public int moveX(){
            return this.x + dx[dir];
        }
        
        public int moveY(){
            return this.y + dy[dir];
        }
        
        public void turn(){
            dir = (dir + 1)%4;
        }
        
        public boolean equals(Point p){
            return this.x == p.x && this.y == p.y;
        }
        
        public String toString(){
            return this.x + " " + this.y;
        }
    }
}
