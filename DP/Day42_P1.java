/*You are navigating a spaceship through a galaxy represented as an m x n space map.  
The spaceship starts from the top-left sector (sector[0][0]) and its mission is 
to safely reach the bottom-right sector (sector[m-1][n-1]).

Each sector on the map can either be clear (0) or blocked by an asteroid field (1).  
The spaceship can only move right or downward at any moment.  
It cannot pass through sectors with asteroid fields.

Find the total number of distinct safe routes the spaceship can take to reach 
its destination at the bottom-right corner.


Input format:
-------------
2 space seperated integers, m & n
next m lines representing the Map 

Output format:
--------------
An integer, the total number of distinct safe routes



Example 1:
----------
Input:
3 3
0 0 0
0 1 0
0 0 0

Output:
2

Explanation:  
There’s one asteroid field blocking the center of the 3×3 map.  
Two possible safe navigation paths:
- Move → Move → Down → Down
- Down → Down → Move → Move

Example 2:
---------
Input:
2 2
0 1
0 0

Output:
1


Constraints:
- m == sectorMap.length
- n == sectorMap[i].length
- 1 <= m, n <= 100
- sectorMap[i][j] is either 0 (clear) or 1 (asteroid field)
 */

import java.util.*;

class Day42_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[m][n];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        System.out.println(findWays(0,0,grid,dp));
    }
    
    
    private static int findWays(int i, int j, int[][] grid, int[][] dp){
        if(i >= grid.length || j >= grid[0].length || i < 0 || j < 0) return 0;
        
        if(grid[i][j]==1) return 0;
        if(i == grid.length-1 && j == grid[0].length - 1){
            return 1;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        
        int next = findWays(i, j+1,grid,dp);
        int down = findWays(i+1, j,grid,dp);
        
        return dp[i][j] = next + down;
    }
}