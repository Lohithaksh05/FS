/*Alex and his twin brother Jordan often create secret messages. One day, Jordan 
gives Alex two encrypted messages and challenges him to find the longest common 
palindromic pattern hidden within both messages.

Alex wants your help to decode the longest common palindromic subsequence that 
exists in both strings.

Your task is to determine the length of the longest subsequence that:
- Appears in both messages
- Is a palindrome

Input Format:
-------------
input1: A string representing the first encrypted message.
input2: A string representing the second encrypted message.

Output Fromat:
--------------
Return an integer representing the length of the longest common palindromic 
subsequence shared by both messages.


Sample Input: 
-------------
adfa
aagh

Sample Output:
--------------
2


Sample Input-2:
---------------
abcda
fxaaba

Sample Output:
--------------
3

Explanation:
------------
The longest palindromic subsequence common to both is "aba" with length 3.

 */


import java.util.*;
class Day68_P1{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        
        String[][] dp = new String[s1.length()][s2.length()];
        String lcc = lcs(s1, s2, 0, 0,dp);
        

        int[][] idp = new int[lcc.length()][lcc.length()];
        System.out.println(lps(lcc,0,lcc.length()-1,idp));
    }
    
    private static String lcs(String s1, String s2, int i, int j,String[][] dp){
        if(i == s1.length() || j==s2.length()){ 
            return "";
        }
        if(dp[i][j] != null) return dp[i][j];
        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = "" + s1.charAt(i) + lcs(s1,s2,i+1,j+1, dp);
        }
        
        String op1 = lcs(s1,s2,i+1,j, dp);
        String op2 = lcs(s1,s2,i,j+1, dp);
        
        return dp[i][j] = (op1.length() >= op2.length()?op1:op2);
    }
    
    private static int lps(String s,int i, int j,int[][]dp){
        if(i >= s.length() || j < 0) return 0;
        if(i == j){
            return 1;
        }
        if(i > j){
            return 0;
        }
        if(dp[i][j] != 0) return dp[i][j];
        if(s.charAt(i) == s.charAt(j)){
            return dp[i][j] = 2 + lps(s,i+1,j-1,dp);
        }
        
        int op1 = lps(s,i+1,j,dp);
        int op2 = lps(s,i,j-1,dp);
        
        return dp[i][j] = Math.max(op1,op2);
    }

}