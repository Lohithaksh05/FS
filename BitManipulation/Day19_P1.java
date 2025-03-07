// AlphaCipher is a string formed from another string by rearranging its letters

// You are given a string S.
// Your task is to check, can any one of the AlphaCipher is a palindrome or not.

// Input Format:
// -------------
// A string S

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// carrace

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// code

// Sample Output-2:
// ----------------
// false


import java.util.*;
class Day19_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int mask = 0;
        for(char c: s.toCharArray()){
            mask ^= 1 << (c-'a');
        }
        // System.out.println(mask);
        System.out.println(mask==0 || (mask&(mask-1))==0);
    }
}