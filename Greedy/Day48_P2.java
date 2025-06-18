/*Venkatadri is a maths teacher.
He is teaching matrices to his students.
He is given a matrix of size m*n, and it contains only positive numbers.
He has given a task to his students to find the special matrix, 
in the iven matrix A[m][n].
A special matrix has following property:
	- The sum of elements in each row, each column and the two diagonals are equal.
	- Every 1*1 matrix is called as a special matrix.
	- The size of the special matrix should be a square, i.e., P*P.

Your task is to help the students to find the speical matrix  with max size P.


Input Format:
-------------
Line-1: Two space separated integers M and N, size of the matrix.
Next M lines: N space separated integers m and n.

Output Format:
--------------
Print an integer, maximum size P of the special matrix.


Sample Input-1:
---------------
5 5
7 8 3 5 6
3 5 1 6 7
3 5 4 3 1
6 2 7 3 2
5 4 7 6 2

Sample Output-1:
----------------
3

Explanation:
------------
The special square is:
5 1 6
5 4 3
2 7 3


Sample Input-2:
---------------
4 4
7 8 3 5
3 2 1 6
3 2 3 3
6 2 3 3

Sample Output-2:
----------------
2

Explanation:
------------
The special square is:
3 3
3 3
 */

import java.util.*;

class Day48_P2{
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
        
        System.out.println(findMax(m,n,grid));
    }
    
    private static int findMax(int m,int n, int[][] grid){
        int[][] rowPref = new int[m][n+1];
        int[][] colPref = new int[m+1][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                rowPref[i][j+1] = rowPref[i][j] + grid[i][j];
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                colPref[i+1][j] = colPref[i][j] + grid[i][j];
            }
        }
        
        for(int len = Math.min(m,n);len>0;len--){
            for(int i=0;i<=m-len;i++){
                for(int j=0;j<=n-len;j++){
                    if(isValid(i,j,len,grid,rowPref,colPref)){
                        return len;
                    }
                }
            }
        }
        return 1;
    }
    
    private static boolean isValid(int r, int c, int len, int[][] grid, int[][] rowPref, int[][] colPref){
        int req = rowPref[r][c + len] - rowPref[r][c];
        
        for(int i=0;i<len;i++){
            int sum  = rowPref[r + i][c + len] - rowPref[r + i][c];
            
            if(req != sum) return false;
        }
        
        for(int j=0;j<len;j++){
            int sum = colPref[r + len][c + j] - colPref[r][c + j];
            if(req != sum) return false;
        }
        int diag = 0;
        for(int i=0;i<len;i++){
            diag += grid[r + i][c + i];
        }
        
        if(diag != req) return false;
        
        int diag2 = 0;
        for(int i=0;i<len;i++){
            diag2 += grid[r + i][c + len - 1 - i];
            
        }
        
        if(diag2     != req) return false;
        
        return true;
    }
}