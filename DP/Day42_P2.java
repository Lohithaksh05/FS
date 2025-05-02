/*A digital advertising company is setting up electronic billboards across the city. 
Each billboard screen has dimensions of rows x cols, indicating how many lines (rows) 
and how many characters per line (cols) the screen can display. The company has 
prepared an advertising slogan consisting of several words, provided as a list of strings. 
The slogan must repeatedly appear on the billboard, word by word, maintaining the 
exact original order. Each word must fit entirely on a single line without breaking. 
Consecutive words on the same line must be separated by exactly one blank space.

Determine how many complete times the given advertising slogan can be displayed 
fully on the billboard screen.

Input format:
-------------
Line 1: Space seperated words, slogon
Line 2: Two space separated integers, rows & cols


Output format:
--------------
An integer, number of times the given advertising slogan can be displayed fully on the billboard screen.


Example 1:
----------
Input=
fast cars
2 8

Output=
1

Explanation:  
fast----  
cars----  
(The character '-' represents empty spaces on the screen.)


Example 2:
----------
Input=
win big now
3 7

Output=
2

Explanation:  
win-big  
now-win  
big-now  
(The character '-' represents empty spaces on the screen.)


Example 3:
----------
Input=
eat fresh daily
4 6

Output=1
 
Explanation:  
eat---  
fresh-  
daily-  
eat---  
(The character '-' represents empty spaces on the screen.)


Constraints:

- 1 <= slogan.length <= 1000
- Each word in slogan consists only of lowercase English letters.
- 1 <= rows, cols <= 2 *10^4 */

import java.util.*;

class Day42_P2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        int m = sc.nextInt();
        int n = sc.nextInt();
        
        System.out.println(countTimes(words, m, n));
    }
    
    private static int countTimes(String[] words, int m, int n){
        int size = words.length;
        int res = 0;
        int idx = 0, i =0;
        int j = 0;
        while(i < m){
            int len = words[idx].length();
            if(len + j <= n){
                j += len + 1;
                idx++;
            }else{
                j = 0;
                i++;
            }
            
            if(idx == size){
                res++;
                idx = 0;
            }
        }
        
        return res;
    }
}