/*Given a classic mobile phone, and the key pad of the phone looks like below.
	1		2		3
		   abc	   def
		 
	4		5		6
   ghi     jkl     mno
  
	7		8		9
  pqrs     tuv    wxyz
	
	*		0		#


You are given a string S contains digits between [2-9] only, 
For example: S = "2", then the possible words are "a", "b", "c".

Now your task is to find all possible words that the string S could represent.
and print them in a lexicographical order. 

Input Format:
-------------
A string S, consist of digits [2-9]

Output Format:
--------------
Print the list of words in lexicographical order.


Sample Input-1:
---------------
2

Sample Output-1:
----------------
[a, b, c]


Sample Input-2:
---------------
24

Sample Output-2:
----------------
[ag, ah, ai, bg, bh, bi, cg, ch, ci]
 */

 import java.util.*;

 class Day34_P3{
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         
         String n = sc.next();
         String[] chars= {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
         
         List<String> lst = new ArrayList<>();
         backtrack(lst, new StringBuilder(),0,n, chars);
         System.out.println(lst);
     }
     
     public static void backtrack(List<String> res, StringBuilder sb, int idx, String digits, String[] arr){
         if(sb.length()==digits.length()){
             res.add(sb.toString());
             return;
         }
         String letters = arr[digits.charAt(idx)-'0'];
         for(int i=0;i<letters.length();i++){
             sb.append(letters.charAt(i));
             backtrack(res, sb, idx+1, digits, arr);
             sb.deleteCharAt(sb.length()-1);
         }
     }
 }