/*
ID: eriksng1
LANG: JAVA
TASK: friday
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class friday {

    private static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int[] freq = new int[7];
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("friday.in"));
        int last = 1900 + sc.nextInt();
        sc.close();
        
        int dayMod, month = 1;
        freq[dayMod = 0]++;
        for(int year = 1900; year < last; year++, month = 0){
            for(; month < 12; month++){
                if(month == 2 && isLeap(year)) {
                    freq[dayMod = (dayMod + monthDays[(month - 1 + 12) %12] + 1) %7]++;
                }
                else {
                    freq[dayMod = (dayMod + monthDays[(month - 1 + 12) % 12]) % 7]++;
                }
            }
        }
        
        PrintWriter p = new PrintWriter(new File("friday.out"));
        for(int i = 0; i < 6; i++) {
            p.print(freq[i] + " ");
        }
        p.println(freq[6]);
        p.close();
        System.exit(0);
    }
    
    private static boolean isLeap(int year){
        if(year%100 == 0)
            return year%400 == 0;
        return year % 4 == 0;
    }
}
