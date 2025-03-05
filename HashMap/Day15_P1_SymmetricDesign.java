// Imagine you are an artisan tasked with assembling a decorative mosaic from a 
// collection of uniquely colored tiles. Each tile is marked with a character, 
// and your challenge is to rearrange these tiles to create a design that mirrors 
// itself perfectly from left to right. 
// Your goal is to determine whether the available tiles can be arranged to form 
// such a symmetric pattern. Print true if a symmetric design is possible,
// and false otherwise.


// Input format:
// A string representing the characters on the tiles.

// Output format:
// Print a boolean value

// Example 1:
// input: work
// output: false

// Example 2:
// input: ivicc
// output: truea

// Constraints:
// 1 <= string.length <= 5000
// tile characters are all lowercase English letters.

package HashMap;
import java.util.*;
public class Day15_P1_SymmetricDesign {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isSymmetric(s));
    }
    
    private static boolean isSymmetric(String s){
        
        Map<Character, Integer> m = new HashMap<>();
        for(char ch : s.toCharArray()){
            m.put(ch,m.getOrDefault(ch,0)+1);
        }
        int c = 0;
        for(int num: m.values()){
            if(num % 2 == 1){
                c++;
            }
            
        }
        return c==0?true:c==1;
    }

}
