/*Aruna as a type writer, typing a document in her laptop. Suddently her system got
hacked and whatever she types as words are displaying in reverse and with simple 
braces. She types only lowercase letters.

Inorder to get the actual words, Aruna has to reverse each word starting with the
word which is in innermost most braces and remove those braces.
Help Aruna to get actual words.

Constraints:
------------
  - 0 <= word.length <= 2000
  - Word only contains lower case English characters and parentheses.
    It's guaranteed that all braces are balanced.


Input Format:
-------------
Line-1: a string represents an encoded word.

Output Format:
--------------
return the original string.


Sample Input-1:
---------------
(pqrs)

Sample Output-1:
----------------
srqp


Sample Input-2:
---------------
(uoy(are)woh)

Sample Output-2:
----------------
howareyou

Explanation
------------
Initially "are" will be revesed as "era".
Then (uoyerawoh) will be reversed.
 */


import java.util.*;

class Day73_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        
        Stack<Integer> st = new Stack<>();
        
        StringBuilder res = new StringBuilder();

        //o(n^2)
        // for(char c: s.toCharArray()){
        //     if(c == ')'){
        //         StringBuilder sb = new StringBuilder();
        //         while(!st.isEmpty() && st.peek()!='('){
        //             sb.append(st.pop());
        //         }
        //         st.pop();
                
        //         for(char ch: sb.toString().toCharArray()){
        //             st.push(ch);
        //         }
        //     }else{
        //         st.push(c);
        //     }
        // }
        
        // for(char c: st){
        //     res.append(c);
        // }
        
        // System.out.println(res);
        


        
        //o(n)
        int[] pairs = new int[s.length()];
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                st.push(i);
            }else if(s.charAt(i)==')'){
                int j = st.pop();
                pairs[i] = j;
                pairs[j] = i;
            }
            
        }
        
        int d = 1;
        for(int i=0;i<s.length();i+=d){
            if(s.charAt(i) =='(' || s.charAt(i) == ')'){
                i = pairs[i];
                d = -d;
            }else{
                res.append(s.charAt(i));
            }
        }
        
        System.out.println(res.toString());
    }
}