/*
The integer array arr[] is given and print the resultant value of the bitwise OR
of the sum of all the possible sub-arrays of the given array arr[].

Note: A sub-array is a sequence of array-elements that could be drawn from other
      sequence of array-elements by removing zero or more elements without changing 
      the order of the remaining elements.



Input Format
-------------
Line-1: Read the size of array P.
Line-2: Read the array elements(space separated values).

Output Format
--------------
Print the resultant value.


Sample input-1:
---------------
4
0 2 1 3
Sample output-1:
----------------
7

Explanation
The sums of sub-arrays are
0 1 2 3 4 5 6. 
and bitwise OR operation
0 OR 1 OR 2 OR 3 OR 4 OR 5 OR 6 = 7.



Sample input-2:
---------------
4
0 0 0 0
Sample output-2:
----------------
0

Explanation
Only one sum sub-arrays i.e. 0.
 */



import java.util.*;
class Day19_P4{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        int sum =0;
        int res = 0;
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
            sum+=arr[i];
            res |= sum;
            res |= arr[i];
        }
        System.out.println(res); 
    }
}