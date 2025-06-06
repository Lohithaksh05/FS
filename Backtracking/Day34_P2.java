/*Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can cunstruct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6


Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false */

import java.util.*;

class Day34_P2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i < n;i++){
            arr[i] = sc.nextInt();
        }
        
        System.out.println(canMakeASquare(arr));
    }
    
    private static boolean canMakeASquare(int[] arr){
        Arrays.sort(arr);
        int sum = 0;
        for(int num: arr){
            sum+=num;
        }
        if(sum % 4 != 0) return false;
        int side = sum / 4;

        
        return backtrack(arr, 0, new boolean[arr.length], side, 0);
        
    }
    
    private static boolean backtrack(int[] arr, int currsum, boolean[]vis, int total, int count){
        if(currsum > total) return false;
        if(currsum == total){
            count++;
            currsum =0;
        }
        if(count==4){
            for(int i=0;i<vis.length;i++){
                if(!vis[i]) return false;
            }
            return true;
        }
        
        for(int i = 0;i < arr.length;i++){
            if(!vis[i]){
                vis[i] = true;
                if(backtrack(arr, currsum + arr[i], vis, total, count)) return true;
                vis[i] = false;
            }
        }
        
        return false;
        
    }
}
