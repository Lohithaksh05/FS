/*
Imagine you're playing a fantasy video game where secret level codes unlock new 
worlds. These codes are strings made up of letters, and a level code is only 
considered valid if every shorter code formed by its leading characters has been
discovered along the journey. In other words, a code is unlockable only when all
of its prefixes are also present in your collection.

Given a list of strings representing the level codes you’ve collected, find the 
longest valid level code such that every prefix of that code is in the list. 
If there is more than one valid code of the same length, choose the one that 
comes first in alphabetical order. If no such code exists, return an empty string.

Input Format
-------------
Line1: Space separated codes (strings)
 
Output Format
--------------
string 


Example 1:
----------
Input:
m ma mag magi magic magici magicia magician magicw
Output: 
"magician"

Explanation: The code "magician" is unlockable because every 
prefix—"m", "ma", "mag", "magi", "magic", "magici", and "magicia"—is present in 
the list. Although "magicw" appears too, it fails since its prefix "magica" is missing.


Example 2:
----------
Input:
a banana app appl ap apply apple
Output: 
"apple"  

Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
comes first in alphabetical order.

Example 3:
----------
Input: 
abc bc ab abcd
Output: 
""
 */

import java.util.*;
class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    
    TrieNode(){
        for(int i=0;i<26;i++){
            children[i] = null;
        }
        isEnd = false;
    }
    
}

class Trie{
    public TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    
    public void insert(String word){
        TrieNode node = root;
        for(char c: word.toCharArray()){
            int idx = c - 'a';
            if(node.children[idx]==null){
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }
    
    public boolean hasAllPrefixes(String word){
        TrieNode node = root;
        int i=0;
        for(char c: word.toCharArray()){
            if(i == word.length()-1) break;
            i++;
            int idx = c - 'a';
            if(node.children[idx]==null || !node.children[idx].isEnd){
                return false;
            }
            node = node.children[idx];
        }
        return true;
    }
}
public class Day27_P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        Trie t = new Trie();
        for(int i=0;i<s.length;i++){
            t.insert(s[i]);
        }
        String longestword = "";
        for(String w: s){
            if(t.hasAllPrefixes(w)){
                if(w.length()>longestword.length()){
                    longestword = w;
                }else if(longestword.length()==w.length() && longestword.compareTo(w)>0){
                    longestword = w;
                }
            }
        }
        
        System.out.print(longestword);
        
        
    }
}
 
 
 
 