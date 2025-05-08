/*A group of researchers is analyzing satellite imagery of agricultural fields, 
represented by a grid of land sections. Each section is marked either as 
fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
need to identify the largest rectangular area consisting exclusively of fertile 
land sections.

Given a binary grid representing the land (1 for fertile and 0 for infertile), 
your task is to compute the area of the largest rectangle consisting entirely 
of fertile land sections.

Input Format:
-------------
Line-1: Two integers rows(r) and columns(c) of grid.
Next r lines: c space separated integers, each row of the grid.

Output Format:
--------------
Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

Example:
--------
input=
4 5
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

output=
6

 */


import java.util.*;

class Day46_P2{
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
        
        System.out.println(largestRectangle(m, n, grid));
        sc.close();
    }
    
    private static int largestRectangle(int m, int n, int[][] grid){
        int[] heights = new int[n];
        int max = 0;
        for(int[] row: grid){
            for(int j = 0;j < n;j++){
                heights[j] = row[j]==0?0:heights[j] + 1;
            }
            
            max = Math.max(max, findLargest(heights));
        }
        
        return max;
    }
    
    private static int findLargest(int[] heights){
        int[] newHeights = new int[heights.length + 1];
        for(int i=0;i<heights.length;i++){
            newHeights[i] = heights[i];
        }
        
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        for(int i=0;i<newHeights.length;i++){
            while(!st.isEmpty() && newHeights[i] < newHeights[st.peek()]){
                int h = newHeights[st.pop()];
                int w = st.isEmpty()?i:i - st.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            st.push(i);
        }
        
        return maxArea;
    }
    
}