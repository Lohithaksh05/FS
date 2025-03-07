// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.
  
// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.


// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5
 
// Sample Output-1:
// ----------------
// 8
 
// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4
 
// Sample Output-2:
// ----------------
// -1

package UnionFind;

import java.util.*;
class DSU{
    int[] parent;
    DSU(int n){
        parent = new int[n+1];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }

    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y){
        int rx = find(x);
        int ry = find(y);
        if(rx==ry) return false;
        if(rx < ry){
            parent[ry] = rx;
        }else{
            parent[rx] = ry;
        }
        return true;
    }
    
}
class Day18_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[][] arr= new int[r][3];
        for(int i=0;i<r;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        DSU d = new DSU(n);
        int components = n;
        int res = 0;
        Arrays.sort(arr, (a,b) -> Integer.compare(a[2],b[2]));
        for(int i=0;i<r;i++){
            if(d.union(arr[i][0],arr[i][1])){
                res += arr[i][2];
                components-=1;
            }
            if(components==1){
                System.out.println(res);
                return;
            }
            
            
        }
        System.out.println(-1);   
    }
    
    
}
