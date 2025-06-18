/*For the given a list of integers, Your task to is find out, the length of the 
longest subsequence that is a toggle sequence.

Toggle Sequence means, the difference between the consecutive numbers
are alternate positive and negative.

For Example:
Given list of integers = 1 3 2 5 4 
the consecutive differences are [ 2, -1, 3, -1], 
the differences are alternate +ve and -ve.
So, complete list is a toggle sequence.

If the list of integers = 1 3 2 4 5,
the consecutive differences are [ 2, -1, 2, 1], not alternate +ve and -ve.
Not a toggle sequence.

Note: A sequence with fewer than two elements is a toggle sequence.

Input Format:
-------------
Space separated Integers, List

Output Format:
--------------
Print the length of the longest toggle sequence


Sample Input-1:
---------------
1 7 4 9 2 5

Sample Output-1:
----------------
6

Explanation:
------------
Given list of integers = 1 7 4 9 2 5
the consecutive differences are [ 6, -3, 5, -7, 3], 
the differences are alternate +ve and -ve.
So, complete list is a toggle sequence.

Sample Input-2:
---------------
1 5 4 3 7 9 10

Sample Output-2:
----------------
4

Explanation:
------------
Given list of integers = 1 5 4 3 7 9 10
There are several subsequences that achieve this length.
One is [1 5 4 7] with differences (4, -1, 3).
 */


import java.util.*;

class Day64_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] nums = new int[s.length];
        
        for(int i=0;i<s.length;i++){
            nums[i] = Integer.parseInt(s[i]);
        }
        
        System.out.println(longestToggleSequence(nums));
    }
    
    private static int longestToggleSequence(int[] nums){
        int[] diffs = new int[nums.length- 1];
        for(int i=1;i<nums.length;i++){
            diffs[i-1] = nums[i] - nums[i - 1];
        }

        return longestAlter(diffs,diffs[0]>0?1:0);
    }
    
    private static int longestAlter(int[] diffs,int pos){
        int count = 1;
        for(int i = 1;i<diffs.length;i++){
            if(diffs[i] == 0) continue;
            int curr = diffs[i]>0?1:0;
            if(curr != pos){
                count++;
                pos = curr;
            }
        }
        
        return count + 1;
        
    }
}