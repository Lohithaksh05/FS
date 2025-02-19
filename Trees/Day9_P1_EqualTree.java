// Balbir Singh is working with networked systems, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false


import java.util.*;
class Day9_P1_EqualTree{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        System.out.println(equalTree(root));
    }
    
    private static TreeNode buildTree(int[] nodes){
        if(nodes.length==0 || nodes[0] == -1) return null; 
        TreeNode root = new TreeNode(nodes[0]);
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        int idx = 1;
        while(!q.isEmpty() && idx<nodes.length){
            TreeNode temp = q.poll();
            
            if(idx<nodes.length && nodes[idx]!=-1){
                temp.left = new TreeNode(nodes[idx]);
                q.offer(temp.left);
                
            }
            idx++;
            if(idx<nodes.length &&nodes[idx]!=-1){
                temp.right = new TreeNode(nodes[idx]);
                q.offer(temp.right);
            }
            idx++;
        }
        
        return root;
    }
    
    
    private static boolean equalTree(TreeNode root){
        helper(root);
        // System.out.println(root.val);
        if(root.val%2!=0) return false;
        return find(root,root.val/2);
    }
    
    
    private static boolean find(TreeNode root,int target){
        if(root==null){
            return false;
        }
        
        if(root.val==target) return true;

        
        return find(root.left,target)||find(root.right,target);
    }
    
    
    private static int helper(TreeNode root){
        if(root.left==null && root.right==null){
            return root.val;
        }
        
        if(root.right==null){
            root.val+=helper(root.left);
        }
        else if(root.left==null){
            root.val+=helper(root.right);
        }else{
            root.val = root.val + helper(root.left) + helper(root.right);
        }
        
        return root.val;
    }
    
    
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
    }
}
