import java.util.*;
public class WordGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String w = sc.next();
        System.out.println(backtrack(w,0));
    }

    public static boolean backtrack(String w, int per){
        for(int i=0;i<w.length()-1;i++){
            if(w.charAt(i)=='A' && w.charAt(i+1)=='A'){
                StringBuilder sb = new StringBuilder(w);
                sb.setCharAt(i, 'B');
                sb.setCharAt(i+1, 'B');
                if(!backtrack(sb.toString(),per==0?1:0)) return true;

                sb.setCharAt(i, 'A');
                sb.setCharAt(i+1, 'A');
            }
        }

        return false;
    }
}
