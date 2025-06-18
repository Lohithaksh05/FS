/*Ravi is playing with strings.Given Three alphabets [a,b,c] , ravi has to 
make strings such that no two adjacents alphabets are same.

For example, He can make strings as "ab", "acb", "b" and "cbabcabcb" which 
are acceptable.where as the strings "bb", "bcc" and "aabbc" are not acceptable.

Given two integers N an2 2d L, Ravi made a list of all acceptable strings of 
length N sorted in lexicographical order.

Return the Lth string of this list or return an empty string if there are less 
than L acceptable strings of length N.

Input Format:
-------------
Line-1: Two space separated integers N and L.

Output Format:
--------------
Print a string result.


Sample Input-1:
---------------
2 3

Sample Output-1:
----------------
ba

Explanation:
-------------
Strings in lexigraphical order are ab,ac,ba,bc,ca,cb. and 3rd one is ba.


Sample Input-2:
---------------
3 4

Sample Output-2:
----------------
acb

Explanation:
------------
Strings in lexigraphical order are aba,abc,aca,acb,bab....

 */

import java.util.*;

class Day73_P2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        List<String> res = new ArrayList<>();
        backtrack(n,new StringBuilder(), res,'z');
        // System.out.println(res);
        System.out.println(res.get(l-1));
    }
    
    private static void backtrack(int n,StringBuilder sb, List<String> res,char prev){
        if(sb.length() == n){
            res.add(sb.toString());
            return;
        }
        char c = 'a';
        for(int i=0;i<3;i++){
            if(c == prev){
                c+=1;
                continue;
            }
            
            sb.append(c);
            backtrack(n, sb, res, c);
            sb.deleteCharAt(sb.length()-1); 
            c+=1;
        }
        
        
        
    }
}