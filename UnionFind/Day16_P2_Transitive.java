// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true


package UnionFind;

import java.util.*;
class DSU{
    int[] parent;
    DSU(){
        parent = new int[26];
        for(int i=0;i<26;i++){
            parent[i] = i;
        }
    }

    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y){
        int rx = find(x);
        int ry = find(y);
        if(rx==ry) return;
        if(rx < ry){
            parent[ry] = rx;
        }else{
            parent[rx] = ry;
        }
    }
}

class Day16_P2_Transitive{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] arr = sc.nextLine().split(" ");
        DSU d = new DSU();
        List<String> l = new ArrayList<>();
        for(String s : arr){
            if(s.charAt(1)=='='){
                d.union(s.charAt(0)-'a',s.charAt(3)-'a');
            }else{
                l.add(s);
            }
        }
        
        for(String s: l){
            if(d.find(s.charAt(0)-'a')==d.find(s.charAt(3)-'a')){
                System.out.println(false);
                return;

            }
        }
        System.out.println(true);
    }
}