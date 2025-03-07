// Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
// cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// The puzzle board has some patterns formed with boxes in it, 
// the patterns may be repeated. The patterns are formed with boxes (1's) only, 
// that are connected horizontally and vertically but not diagonally.

// Pranav wants to find out the number of unique patterns in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of unique patterns in 
// the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the grid-land.
// Next M lines: contains N space-separated integers [0, 1].

// Output Format:
// --------------
// Print an integer, the number of unique patterns in the puzzle board.


// Sample Input-1:
// ---------------
// 5 5
// 0 1 0 1 1
// 1 1 1 0 1
// 0 1 0 1 0
// 1 0 1 1 1
// 1 1 0 1 0

// Sample Output-1:
// ----------------
// 3

// Explanation-1:
// ------------
// The unique patterns are as follows:
//   1			1 1	    1 
// 1 1 1		  1 ,	1 1
//   1	   ,	
   
   
// Sample Input-2:
// ---------------
// 6 6
// 1 1 0 0 1 1
// 1 0 1 1 0 1
// 0 1 0 1 0 0
// 1 1 0 0 0 1
// 0 0 1 0 1 1
// 1 1 0 1 0 0

// Sample Output-2:
// ----------------
// 5

// Explanation-2:
// ------------
// The unique patterns are as follows:
// 1 1		1 1		    1		1 1	,	1
// 1   ,     1 ,	    1 1 ,		



import java.util.*;

class Day18_P2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j = 0;j<n;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        
        System.out.println(uniquePatterns(grid,m,n));
        
    }
    
    private static int uniquePatterns(int[][] grid, int m, int n){
        Set<String> s = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(grid[i][j]==1){
                    bfs(i,j,grid, s);
                }
            }
        }
        return s.size();
    }
    
    private static void bfs(int i, int j , int[][] grid, Set<String> s){
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});
        
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int[] dir:dirs){
                int dx = temp[0] + dir[0];
                int dy = temp[1] + dir[1];
                if(dx>=0 && dx<m && dy>=0 && dy<n && grid[dx][dy]==1){
                    sb.append(dx-i).append(dy-j);
                    q.offer(new int[]{dx,dy});
                    grid[dx][dy] = 0;
                }
            }
        }
        s.add(sb.toString());
    }
}