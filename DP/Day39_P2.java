/*Amogh is an Antiquarian, The person who collects antiques.
He found a rear keyboard which has following keys,
Keys are 'N', 'S', 'C' and 'P'

1st Key - 'N': Print one character 'N' on Console.
2nd Key - 'S': Select the whole Console.
3rd Key - 'C': Copy selected content to buffer.
4th Key - 'P': Print the buffer on Console, and append it after what has 
already been printed.

Now, your task is to find out maximum numbers of 'N's you can print
after K keystrokes . 

Input Format:
-------------
An integer K

Output Format:
--------------
Print an integer, maximum numbers of 'N's you can print.


Sample Input-1:
-------------------
3

Sample Output-1:
-------------------- 
3

Explanation: 
---------------
We can print at most get 3 N's on console by pressing following key sequence:
N, N, N



Sample Input-2:
-------------------
7

Sample Output-2:
---------------------
9

Explanation: 
---------------
We can print at most get 9 N's on console by pressing following key sequence:
N, N, N, S, C, P, P
 */

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        
        System.out.println(maxN(k, new int[k+1]));
    }
    
    private static int maxN(int k, int[] dp){
        if(k <= 0) return 0;
        if(dp[k]!=0) return dp[k]; 
        int max = maxN(k-1, dp) + 1;
        
        for(int i=1;i<k-2;i++){
            int numPastes = k - i - 2;
            max = Math.max(max, maxN(i, dp)*(numPastes+1));
        }
        
        return dp[k] = max;
        
    }
    
}