/*
ID: eriksng1
LANG: JAVA
TASK: zerosum
*/


import java.io.*;
import java.util.Stack;

public class zerosum {
    
    static Stack<Character> exp;
    static char[] ops;
    static int N;
    static PrintWriter p;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
        p = new PrintWriter(new File("zerosum.out"));
        
        N = Integer.parseInt(in.readLine().trim());
        
        exp = new Stack<Character>();
        ops = new char[N - 1];
        exp.push((char)(1 + '0'));
        solve(2);
        
        p.close();
        System.exit(0);
    }
    
    static void solve(int value){
        if(value > N){
            exp.push('.');
            if(properSum())
                print();
            exp.pop();
            return;
        }
        
        char c = (char) (value + '0');
        exp.push(c);
        ops[value - 2] = ' ';
        solve(value + 1);
        exp.pop();
        
        exp.push('+');
        exp.push(c);
        ops[value - 2] = '+';
        solve(value + 1);
        exp.pop();
        exp.pop();
        
        exp.push('-');
        exp.push(c);
        ops[value - 2] = '-';
        solve(value + 1);
        exp.pop();
        exp.pop();
    }
    
    static boolean properSum(){
        int val = 0;
        int index = 0;
        while(index < exp.size()){
            if(exp.get(index) < '1'){
                break;
            }
            val = 10 * val + exp.get(index ++) - '0';
        }
        
        char operator = exp.get(index++);
        int nextNum = 0;
        out: for(; index < exp.size(); index++){
            char temp = exp.get(index);
            if(temp < '1'){
                switch(operator){
                    case '+': val += nextNum;break;
                    case '-': val -= nextNum;break;
                }
                nextNum = 0;
                operator = temp;
            } else {
                nextNum = nextNum * 10 + temp - '0';
            }
            
        }
        
        return val == 0;
    }

    static void print(){
        p.print(1);
        for(int i = 1; i < N; i++){
            p.print(ops[i - 1]);
            p.print(i + 1);
        }
        p.println();
    }
}
