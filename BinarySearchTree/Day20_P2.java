/*
 Imagine you're the chief curator at a renowned museum that houses a rare collection 
of ancient artifacts. These artifacts are arranged in a special display that 
follows a strict rule: any artifact positioned to the left of another has a lower 
value, and any artifact on the right has a higher value. 

Your task is to transform this display into what is known as a "Greater Artifact 
Display." In this new arrangement, each artifact’s new value will be its original 
value plus the sum of the values of all artifacts that are more valuable than it.

Example 1:
----------
input=
4 2 6 1 3 5 7
output=
22 27 13 28 25 18 7

Explanation:
Input structure 
       4
      / \
     2   6
    / \ / \
   1  3 5  7

Output structure:
        22
      /   \
     27   13
    / \   / \
   28 25 18  7

Reverse updates:
- Artifact 7: 7
- Artifact 6: 6 + 7 = 13
- Artifact 5: 5 + 13 = 18
- Artifact 4: 4 + 18 = 22
- Artifact 3: 3 + 22 = 25
- Artifact 2: 2 + 25 = 27
- Artifact 1: 1 + 27 = 28

 */

import java.util.*;
 class Day20_P2{ 
     private static int sum = 0;
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         
         String[] a = sc.nextLine().split(" ");
         int[] tree = new int[a.length];
         for(int i=0;i<a.length;i++){
             tree[i] = Integer.parseInt(a[i]);
         }
         
         TreeNode root = buildTree(tree);
         modify(root);
         levelorder(root);
         
     }
     private static void modify(TreeNode root){
         if(root==null){
             return ;
         }
         modify(root.right);
         root.val+=sum;
         sum = root.val;
         modify(root.left);
         
         
     }
     
     private static TreeNode buildTree(int[] nodes){
         if(nodes.length==0 || nodes[0] == -1) return null; 
         TreeNode root = new TreeNode(nodes[0]);
         
         Queue<TreeNode> q = new LinkedList<>();
         
         q.offer(root);
         int idx = 1;
         while(!q.isEmpty() && idx<nodes.length){
             TreeNode temp = q.poll();
             
             if(idx<nodes.length && nodes[idx]!=-1 && nodes[idx]<temp.val){
                 temp.left = new TreeNode(nodes[idx]);
                 q.offer(temp.left);
                 
             }
             idx++;
             if(idx<nodes.length &&nodes[idx]!=-1 && nodes[idx]>temp.val){
                 temp.right = new TreeNode(nodes[idx]);
                 q.offer(temp.right);
             }
             idx++;
         }
         
         return root;
     }
     private static void levelorder(TreeNode root){
         Queue<TreeNode> q = new LinkedList<>();
         
         q.offer(root);
         while(!q.isEmpty()){
             int size = q.size();
             List<Integer> levelNodes = new ArrayList<>();
             for(int i=0;i<size;i++){
                 TreeNode temp = q.poll();
                 System.out.print(temp.val + " ");
                 if(temp.left!=null){
                     q.offer(temp.left);
                 }
                 if(temp.right!=null){
                     q.offer(temp.right);
                 }
             }
             
             
         }
     }
 }
 
 class TreeNode{
     int val;
     TreeNode left, right;
     TreeNode(int val){
         this.val = val;
     }
 }