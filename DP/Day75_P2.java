/*Radhika is leading a disaster response mission and must divide her team’s 
resources into two operational units: Unit Alpha and Unit Beta.

Each resource is represented by a positive integer denoting its capacity score, 
given in the array resources[].

To ensure both units are equally capable, Radhika must split the resources 
into two non-empty ordered groups such that:
	- Every resource is assigned to exactly one unit
	- The sum of capacity scores in both units is at least k

A split is called a valid deployment plan (or great partition) if both groups have 
a total capacity ≥ k. Since the number of deployment plans can be huge, return 
the number of distinct valid deployment plans modulo 10⁹ + 7.

Two plans are considered distinct if at least one resource is assigned to a different unit in each plan.

Input Format
------------
First line: An integer k — the minimum required capacity per unit.
Second line: An integer n — the number of resources.
Third line: n space-separated integers representing resource capacities.

Output Format
---------------
A single integer: the number of valid deployment plans modulo 10⁹ + 7

Constraints
-----------
1 ≤ n, k ≤ 1000
1 ≤ resources[i] ≤ 10

Sample Input
--------------
4
4
1 2 3 4

Sample Output
---------------
6
 
Explanation
------------
These are the valid deployment plans (where each group ha sum ≥ 4):
[1, 2, 3] and [4]
[1, 3] and [2, 4]
[1, 4] and [2, 3]
[2, 4] and [1, 3]
[2, 3] and [1, 4]
[4] and [1, 2, 3]


Sample Input
--------------
4
4
1 2 1 2

Sample Output
---------------
0 */

// import java.util.*;

// class Day75_P2{
// 	public static void main(String[] args) {
// 		Scanner sc = new Scanner(System.in);
// 		int k = sc.nextInt();
// 		int n = sc.nextInt();

// 		int[] arr = new int[n];
// 		int sum = 0;
// 		for(int i=0;i<n;i++){
// 			arr[i] = sc.nextInt();
// 			sum += arr[i];
// 		} 
// 		int MOD = (int)1e9 + 7;
// 		int[][] dp = new int[n + 1][k];
// 		for(int[] row: dp){
// 			Arrays.fill(row, -1);
// 		}
// 		int maxp = maxPartitions(arr, 0, dp, 0, k);
// 		// int valid = ((1<<n));
// 		System.out.println((1<<n) - maxp*2);
// 		// System.out.println(maxp);
// 		// System.out.println(valid);
// 	}

// 	private static int maxPartitions(int[] arr, int idx, int[][]dp, int currsum, int k){
// 		if(currsum >= k) return 0;
// 		if(idx == arr.length){
// 			return currsum > 0?1:0;
// 		}

// 		if(dp[idx][currsum] != -1){
// 			return dp[idx][currsum];
// 		}
		 
// 		int take = maxPartitions(arr, idx + 1, dp, currsum + arr[idx],k);
		
// 		int skip = maxPartitions(arr, idx + 1, dp, currsum, k);


// 		return dp[idx][currsum] = take + skip;
// 	}
	
// }

import java.util.*;

public class Day75_P2 {

    static final int MOD = 1_000_000_007;
    static int[][] memo;
    static int[] resources;
    static int n, k;

    // Recursive DP function: count subsets starting from 'index' with current sum 'currSum'
    static int countInvalidSubsets(int index, int currSum) {
        if (currSum >= k) return 0; // Only consider subsets with sum < k
        if (index == n) return currSum > 0 ? 1 : 0; // Only count non-empty subsets

        if (memo[index][currSum] != -1) return memo[index][currSum];

        // Option 1: Include current element
        int include = countInvalidSubsets(index + 1, currSum + resources[index]);

        // Option 2: Exclude current element
        int exclude = countInvalidSubsets(index + 1, currSum);

        return memo[index][currSum] = (include + exclude) % MOD;
    }

    static int powerOfTwo(int x) {
        long result = 1;
        long base = 2;
        while (x > 0) {
            if ((x & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            x >>= 1;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        k = sc.nextInt();
        n = sc.nextInt();
        resources = new int[n];
        for (int i = 0; i < n; i++) {
            resources[i] = sc.nextInt();
        }

        // Total capacity check: if total sum < 2k, no valid partition possible
        int total = 0;
        for (int r : resources) total += r;
        if (total < 2 * k) {
            System.out.println(0);
            return;
        }

        // Initialize memo table
        memo = new int[n + 1][k];
        for (int[] row : memo) Arrays.fill(row, -1);

        // Count invalid subsets (sum < k)
        int invalid = countInvalidSubsets(0, 0);
        long invalidPlans = (2L * invalid) % MOD;

        // Total valid plans = 2^n - invalidPlans
        int totalPlans = powerOfTwo(n);
        long validPlans = (totalPlans - invalidPlans + MOD) % MOD;

        System.out.println(validPlans);
    }
}
