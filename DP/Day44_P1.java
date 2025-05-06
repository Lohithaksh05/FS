/*Archaeologists discovered an ancient manuscript represented by a string 'text', 
where each character represents an ancient symbol. They suspect the manuscript 
might contain repeated symbol patterns (substrings).

Your task is to analyze the text and determine the length of the longest 
repeating symbol pattern. If the text contains no repeating patterns, return '0'.

Example:
--------
Input=
scarabankhscarab

Output=
6

Explanation: The longest repeating symbol pattern is "scarab", appearing twice.

 Constraints:
- 1 <= text.length <= 2000
- 'text' consists of lowercase English letters ('a' - 'z').
 */

import java.util.*;
class Day44_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lrs(s));
    }
    
    private static int lrs(String s){
        int n = s.length();
        for(int len=n-1;len>=1;len--){
            Set<String> set = new HashSet<>();
            for(int i=0;i<=n - len;i++){
                String temp = s.substring(i,i+len);
                if(set.contains(temp)) return len;
                set.add(temp);
            }
        }
        
        return 0;
    }
}