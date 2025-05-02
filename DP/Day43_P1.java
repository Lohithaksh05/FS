/* You are managing a fleet of exploratory spacecraft, each carrying containers 
composed of two types of critical resources: 
fuel units (represented by '0') and oxygen tanks (represented by '1'). 
You're given a list 'containers', where each container is represented by a 
binary string indicating its resource composition, 
along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
'oxygenLimit' (maximum allowed oxygen tanks).

Your goal is to select the largest possible subset of containers such that the 
total number of fuel units does not exceed 'fuelLimit' and the total number of 
oxygen tanks does not exceed 'oxygenLimit'.

Input format:
-------------
Line 1: Space seperated strings, represents the container strings
Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

Output format:
--------------
An integer, largest possible subset of containers.


Example 1:
----------
Input=
10 0001 111001 1 0
5 3

Output=
4

Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
- Total fuel units = 5 (within limit)
- Total oxygen tanks = 3 (within limit)
Container "111001" cannot be included as it exceeds the oxygen tank limit.


Example 2:
----------
Input=
10 0 1
1 1

Output=
2

Explanation: The largest subset meeting the constraints is {"0", "1"}.
- Total fuel units = 1
- Total oxygen tanks = 1


Constraints:
- 1 <= containers.length <= 600
- 1 <= containers[i].length <= 100
- 'containers[i]' consists only of digits '0' and '1'.
- 1 <= fuelLimit, oxygenLimit <= 100
*/

import java.util.*;

class Day43_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        
        int fuel = sc.nextInt();
        int oxygen = sc.nextInt();
        int[][][] dp = new int[arr.length+1][fuel+1][oxygen+1];
        int[] onec = counts(arr);
        System.out.println(findLargestSubset(arr,fuel,oxygen,0,0,0,dp,onec));
        
    }

    private static int[] counts(String[] arr){
        int[] res = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            for(char c: arr[i].toCharArray()){
                if(c == '1') res[i]++;
            }
        }

        return res;
    }

    private static int findLargestSubset(String[] arr, int fuel, int oxygen, int idx, int f, int o,int[][][] dp, int[] counts){
        if(f > fuel || o > oxygen) return 0;
        if(idx >= arr.length) return 0;
        
        if(dp[idx][f][o] != 0) return dp[idx][f][o];
        int pick = 1 + findLargestSubset(arr, fuel, oxygen, idx + 1, f + arr[idx].length() - counts[idx], o + counts[idx], dp,counts);
        int notpick = findLargestSubset(arr, fuel,oxygen, idx + 1,f, o,dp,counts);

        return dp[idx][f][o] = Math.max(pick,notpick);
    }
}