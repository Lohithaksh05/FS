import java.util.*;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] grid = new char[m][n];
        for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                grid[i][j] = sc.next().charAt(0);
            }
            
        }
        
        int max = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='0'){
                    int countd = 0;
                    for(int k = j + 1;k<n;k++){
                        if(grid[i][k]=='B') break;
                        if(grid[i][k]=='D') countd++;
                    }
                    
                    for(int k = j - 1;k>=0;k--){
                        if(grid[i][k]=='B')break;
                        if(grid[i][k]=='D') countd++;
                    }
                    
                    for(int k = i+1;k<m;k++){
                        if(grid[k][j]=='B')break;
                        if(grid[k][j]=='D')countd++;
                    }
                    for(int k = i-1;k>=0;k--){
                        if(grid[k][j]=='B')break;
                        if(grid[k][j]=='D')countd++;
                    }
                    
                    max = Math.max(max, countd);
                }
                
            }
        }
        
        System.out.println(max);
    }
    
    
}