/*
A detective is investigating a case involving a secret message hidden within a 
longer note. The detective knows that the culprit rearranged the letters of a 
short code-word into multiple secret locations within a larger note.

Given two strings, note (the longer text) and codeWord (the short secret code), 
your task is to help the detective find all starting positions within the note 
where any rearrangement or shuffled of codeWord is located.

Input Format:
-------------
Single line space separated strings, two words.

Output Format:
--------------
Print the list of integers, indices.


Sample Input-1:
---------------
bacdgabcda abcd
 
Sample Output-1:
----------------
[0, 5, 6]

Explanation:
- At index 0: "bacd" is an anagram of "abcd"
- At index 5: "abcd" matches exactly
- At index 6: "bcda" is an anagram of "abcd"
Thus, the positions [0, 5, 6] are returned.

Sample Input-2:
---------------
bacacbacdcab cab

Sample Output-2:
----------------
[0, 3, 4, 5, 9]

 */

 import java.util.*;
 class Day32_P1{
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         String s1 = sc.next();
         String s2 = sc.next();
         Map<Character,Integer> codemp = new HashMap<>();
         for(char c: s2.toCharArray()){
             codemp.put(c, codemp.getOrDefault(c,0)+1);
         }
         Map<Character, Integer> longmp = new HashMap<>();
         int n = s1.length();
         int l = 0;
         List<Integer> res = new ArrayList<>();
         for(int r = 0;r < n;r++){
             longmp.put(s1.charAt(r),longmp.getOrDefault(s1.charAt(r),0)+1);
     
             if(r - l + 1 == s2.length()){
                 if(longmp.equals(codemp)){
                     res.add(l);
                 }    
                 if(longmp.get(s1.charAt(l))==1){
                     longmp.remove(s1.charAt(l));
                 }else{
                     longmp.put(s1.charAt(l),longmp.get(s1.charAt(l))-1);
                 }
                 l++;
             }
         }
         System.out.println(res);
     }
 }