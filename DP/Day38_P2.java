/*You are organizing a grand parade where 'N' marching bands will move in a straight 
line. Each band must wear uniforms of exactly one color chosen from 'K' available 
colors. To keep the parade visually appealing and avoid monotony, 
you must follow this important guideline:

- No more than 'two consecutive bands' can wear 'uniforms of the same color'.

Given the total number of bands N and the number of uniform color choices K, 
determine the total number of valid ways you can assign uniform colors to all 
bands so that the above rule is not violated.

Input Format:
-------------
Two integers N and K.

Output Format:
--------------
Print an integer, Number of ways.

Example 1:  
----------
Input: 
3 2
Output:
6  

Explanation:
------------
Bands	band-1	band-2	band-3
----- 	----- 	----- 	-----
1		c1 		c1		c2
2		c1 		c2 		c1
3		c1 		c2 		c2
4		c2 		c1 		c1
5		c2 		c1 		c2
6		c2 		c2 		c1

Example 2:  
----------
Input: 
1 1
Output: 
1


Constraints:  
- 1 <= n <= 50  
- 1 <= k <= 10^5 
- The result will always be within the range of a 32-bit signed integer.
*/

import java.util.*;
class Day38_P2{
    static int n,k;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        int ans = 0;
        Map<String, Integer> memo = new HashMap<>();
        for(int i=0;i<k;i++){
            ans += countMaxWays(1, i, 1,memo);
        }
        System.out.println(ans);
    }
    
    private static int countMaxWays(int idx, int prev, int count, Map<String,Integer> memo){
        if(idx == n) return 1;
        
        
        String s = idx + "" + prev + "" + count;
        
        if(memo.containsKey(s)) return memo.get(s);
        int total = 0;
        for(int i=0;i<k;i++){
            if(i == prev && count == 2){
                continue;
            }
            if(i == prev){
                total += countMaxWays(idx + 1,i, count + 1, memo);
            }else{
                total += countMaxWays(idx + 1, i, 1, memo);
            }
        }
        memo.put(s, total);
        return memo.get(s);
    }
}