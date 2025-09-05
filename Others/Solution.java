// Somesh is given a task to you on Strings.
// You have given a string S ([a-z]), and an integer array Arr[]
// Now your task is to modify 'S' in such way:
// replace the 'i+1' characters in the string, with next i-th character 
// in alphabetic order(cyclic).

// For example, if S="art", Arr[]=[2,3,5] is 3, 
// i=0, modify('a') = 'c' , S="crt".
// i=1, modify('c') = 'f', modify('r') = 'u', S="fut".
// i=2, modify('f') = 'k', modify('u') = 'z', modify('t') = 'y', S="kzy"
// Finally modified string is kzy. 

// Note: if arr[i]=3 modify('z') ='c'

// Return the final modified string after all such modifications to S are applied.

// Input Format:
// -------------
// Line-1 -> A String S, length of S is L
// Line-2 -> L space separated integers.

// Output Format:
// --------------
// Print modifed String.


// Sample Input-1:
// ---------------
// adbp
// 1 2 3 4

// Sample Output-1:
// ----------------
// kmit


import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        String s = sc.nextLine();
        String[] arr = sc.nextLine().split(" ");
        
        int[] nums = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            nums[i] = Integer.parseInt(arr[i]);
        }
        System.out.println(modify(s,nums));
        
    }
    
    public static String modify(String s, int[] arr){
        StringBuilder sb = new StringBuilder();
        
        int[] suffsum = new int[arr.length];
        
        suffsum[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){
            suffsum[i] = arr[i]  + suffsum[i+1];
        }
        for(int i=0;i<s.length();i++){
            int idx = s.charAt(i) - 'a';
            int newidx = (idx + suffsum[i])%26;
            sb.append((char)('a' + newidx));
        }
        
        return sb.toString();
    }
}