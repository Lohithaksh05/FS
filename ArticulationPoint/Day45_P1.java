/*In a faraway galaxy, interstellar explorers have mapped N planets, numbered from 
0 to N-1, interconnected through space routes represented by the given 'routes'. 
Each element routes[i] = [ai, bi] denotes a direct space route between planet 
'ai' and planet 'bi'.

A Critical Gateway Planet (also known as an articulation point) is a special 
planet whose removal (along with its space routes) increases the number of 
disconnected Galactic Regions, thereby isolating groups of planets from each other.

Given the number of planets (N), number of routes (M) and a list of direct space 
routes (routes), identify and list all the Critical Gateway Planets within this galaxy.

Input Format:
-------------
Line-1: Two space separated integers N and M, number of planets and routes
Next M lines: Two space separated integers ai and bi.
 
Output Format:
--------------
Print an integer, number of disconnected Galactic Regions.

Example 1:
----------
Input=
5 5
0 1
1 2
2 0
1 3
3 4

Output=
[1, 3]

Explanation:
Removing planet 1 disconnects the galaxy into two separate regions: {0,2} and {3,4}.
Removing planet 3 isolates planet 4, increasing the number of Galactic Regions.


Example 2:
-----------
Input=
4 3
0 1
1 2
2 3

Output=
[1, 2]

Explanation:
Removing planet 1 or 2 increases the Galactic Regions from 1 to 2.


Constraints:
- 1 <= n <= 2000
- 1 <= routes.length <= 5000
-  routes[i].length == 2
- 0 <= ai <= bi < n
- ai != bi
- No repeated space routes exist (routes).
 */

import java.util.*;
class Day45_P1{
    static int time = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            
            graph[u].add(v);
            graph[v].add(u);
        }
        
        boolean[] vis = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        boolean[] ap = new boolean[n];
        
        Arrays.fill(parent, -1);
        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(i,vis,disc,low,parent,ap,graph);
            }
        }
        
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(ap[i]) res.add(i);
        }
        
        System.out.println(res);
    }
    
    private static void dfs(int u, boolean[] vis, int[] disc, int[]low, int[] parent,boolean[] ap, List<Integer>[] graph){
        vis[u] = true;
        disc[u] = low[u] = ++time;
        
        int children = 0;
        for(int v: graph[u]){
            if(!vis[v]){
                children++;
                parent[v] = u;
                dfs(v,vis,disc,low,parent,ap,graph);
                
                low[u] = Math.min(low[u],low[v]);
                if(parent[u]==-1 && children > 1){
                    ap[u] = true;
                }
                if(parent[u]!=-1 && low[v] >= disc[u]){
                    ap[u] = true;
                }
            }else if(v!=parent[u]){
                low[u] = Math.min(low[u],disc[v]);
            }
        }
        
    }
}