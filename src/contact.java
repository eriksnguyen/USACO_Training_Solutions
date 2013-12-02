/*
ID: eriksng1
LANG: JAVA
TASK: contact
*/


import java.io.*;
import java.util.*;

public class contact {

    static int A, B, N;
    static String bits;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("contact.in"));
        A = sc.nextInt();
        B = sc.nextInt();
        N = sc.nextInt();
        
        StringBuilder b = new StringBuilder(20000);
        while(sc.hasNext()){
            b.append(sc.next());
        }
        
        bits = b.toString();
        
        HashMap<String, Integer> map = new HashMap<String, Integer>((int) Math.pow(2, B + 1));
        for(int i = A; i <= B; i++){
            for(int j = 0; j <= bits.length() - i; j++){
                String s = bits.substring(j, j + i);
                if(!map.containsKey(s)) {
                    map.put(s, 1);
                }
                else {
                    map.put(s, map.get(s) + 1);
                }
            }
        }
        
        TreeMap<Integer, ArrayList<String>> list = new TreeMap<Integer, ArrayList<String>>(new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
            
        });
        
        for(String s : map.keySet()){
            Integer i = map.get(s);
            if(!list.containsKey(i)){
                list.put(i, new ArrayList<String>());
            }
            list.get(i).add(s);
        }
        
        int printed = 0;
        
        PrintWriter p = new PrintWriter(new File("contact.out"));
        for(Integer i : list.keySet()){
            if(printed >= N) {
                break;
            }
            ArrayList<String> out = list.get(i);
            Collections.sort(out, new Comparator<String>(){

                @Override
                public int compare(String o1, String o2) {
                    if(o1.length() == o2.length())
                        return o1.compareTo(o2);
                    return o1.length() - o2.length();
                }
                
            });
            p.println(i);
            StringBuilder bout = new StringBuilder();
            for(int j = 0; j < out.size(); j++){
                bout.append(out.get(j)).append(" ");
                if(j%6 == 5){
                    p.println(bout.toString().trim());
                    bout = new StringBuilder();
                }
            }
            if(bout.length() > 0)
                p.println(bout.toString().trim());
            printed ++;
            
        }
        p.close();
        System.exit(0);
    }

}
