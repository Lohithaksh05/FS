/*You are a robot explorer navigating a vast digital maze stored as a string of digits. 
Each digit or pair of digits on the path represents a movement instruction, which 
translates to a specific direction using the following map:

"1" → Move 'A'

"2" → Move 'B'

...

"26" → Move 'Z'

However, the maze has tricky encoding rules. Sometimes a single digit can be 
read alone, and sometimes two digits together form a valid move — but not every 
combination is valid. For example, "05" is invalid, while "5" is fine. 
Your robot can only navigate using valid instruction steps, and you must find 
how many unique navigation sequences the robot can follow to reach the end of 
the maze.

Given the string s of digits, determine the total number of distinct ways the 
robot can interpret and follow the path from start to end without making an 
invalid move.

If no valid navigation is possible, return 0.


Input Format:
-------------
A string s.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
123

Sample Output-1:
----------------
3

Explanation:
------------
123 can be converted as: ABC, LC, AW


Sample Input-2:
---------------
326

Sample Output-2:
----------------
2

Explanation:
------------
326 can be converted as: CBF, CZ
 */

import java.util.*;

class Day38_P3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        System.out.println(helper(s, 0, dp));
    }
    
    private static int helper(String s, int idx, int[] dp){
        if(idx >= s.length()) return 1;
        if(s.charAt(idx)=='0') return 0;
        
        if(dp[idx] != -1) return dp[idx];
        int ans = 0;
        
        if(idx < s.length()-1 && (Integer.parseInt("" +s.charAt(idx)+s.charAt(idx+1)) <= 26)){
            ans += helper(s, idx + 2, dp);
        }
        
        ans+= helper(s, idx + 1, dp);
        return dp[idx] = ans;
        
    }
}