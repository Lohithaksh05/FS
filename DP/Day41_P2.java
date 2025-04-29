/*You are working in a genetics laboratory where you are tasked with correcting 
DNA sequences. Each DNA strand is represented as a sequence of characters 
'A', 'C', 'G', and 'T'.
Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
must be modified to match a targetDNA sequence exactly.

You can perform the following mutation operations:
- Insert a nucleotide (A, C, G, or T) into the DNA strand.
- Delete a nucleotide from the DNA strand.
- Replace a nucleotide with another one.

Each operation counts as one step.

Your task is to find the minimum number of mutation operations needed to 
transform the originalDNA into the targetDNA.

Input format:
-------------
2 space seperated strings, originalDNA and targetDNA

Output format:
--------------
An integer, the minimum number of mutation operations


Example 1:
-----------
Input:
ACGT AGT

Output:
1

Explanation:
Delete 'C': "ACGT" → "AGT"
Only 1 mutation is needed.

Example 2:
----------
Input:
GATTAC GCATGCU

Output:
4

Explanation:
- Replace 'A' with 'C': "GATTAC" → "GCTTAC"
- Replace 'T' with 'A': "GCTTAC" → "GCATAC"
- Replace 'A' with 'G': "GCATAC" → "GCATGC"
- Insert 'U' at the end: "GCATGC" → "GCATGCU"

Thus, 4 mutations are needed.
 */


import java.util.*;
class Day41_P2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s1 = sc.next();
        String s2 = sc.next();
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        System.out.println(findOps(s1,s2,0,0,dp));
        
    }
    
    private static int findOps(String s1, String s2, int i, int j,int[][] dp){
        if(i == s1.length()) return s2.length() - j;
        if(j == s2.length()) return s1.length() - i;
        
        if(dp[i][j]!=0) return dp[i][j];
        
        if(s1.charAt(i)==s2.charAt(j)){
            return dp[i][j] = findOps(s1,s2, i+1,j+1, dp);
        }
        
        int insert = findOps(s1,s2,i,j+1, dp)+1;
        int delete = findOps(s1,s2,i+1,j, dp)+1;
        int replace = findOps(s1,s2,i+1,j+1, dp)+1;
        
        return dp[i][j] = Math.min(insert, Math.min(delete, replace));
    }
}